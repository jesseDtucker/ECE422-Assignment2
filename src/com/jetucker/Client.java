package com.jetucker;

import com.google.protobuf.InvalidProtocolBufferException;
import com.json.exceptions.JSONParsingException;
import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;

public final class Client
{
    static String s_folderRoot = "./";

    private static final class Config
    {
        String folderRoot = s_folderRoot;
        String serverAddress = "127.0.0.1";
        int serverPort = 16000;
        int userId = 0;
        byte[] key = null;
    }

    private static Response.ControlResponse PlaceRequest(Request.ControlRequest request,
                                                         byte[] key,
                                                         DataOutputStream outputStream,
                                                         DataInputStream inputStream)
    {
        Response.ControlResponse response = null;
        // encrypt message
        Encryptor encryptor = new Encryptor();
        byte[] unencryptedRequest = request.toByteArray();
        byte[] encryptedRequest = encryptor.Encrypt(unencryptedRequest, key);

        // send message
        try
        {
            outputStream.writeInt(encryptedRequest.length);
            outputStream.writeInt(unencryptedRequest.length);
            outputStream.write(encryptedRequest);
            outputStream.flush();

            // get response
            int totalSize = inputStream.readInt();
            int msgSize = inputStream.readInt();
            byte[] encryptedResponse = new byte[totalSize];
            inputStream.readFully(encryptedResponse);

            // decrypt response
            byte[] rawResponse = encryptor.Decrypt(encryptedResponse, key);
            byte[] truncatedResponse = Arrays.copyOf(rawResponse, msgSize);
            try
            {
                response = Response.ControlResponse.parseFrom(truncatedResponse);
                if(response.getUserId() != request.getUserId())
                {
                    System.out.println("Received response for wrong userID!");
                    response = null;
                }
            }
            catch(InvalidProtocolBufferException ex)
            {
                System.out.println("Received response from server, but could not decode it!");
            }
        }
        catch (IOException ex)
        {
            System.out.println("Failed to send request to server : ");
            System.out.println(ex.getMessage());
        }

        return response;
    }

    private static boolean Authenticate(long userId, byte[] key, DataOutputStream outStream, DataInputStream inStream)
    {
        boolean isAuthed = false;

        // compose message
        Request.ControlRequest.Builder builder = Request.ControlRequest.newBuilder();
        builder.setControlCode(Request.RequestControlCode.REQUEST_AUTH);
        builder.setUserId(userId);
        Request.ControlRequest request = builder.build();

        Response.ControlResponse response = PlaceRequest(request, key, outStream, inStream);
        if(response != null)
        {
            switch (response.getControlCode())
            {
                case RESPONSE_AUTH:
                    isAuthed = HandleAuthResponse(response);
                    break;
                case RESPONSE_ERROR:
                    HandleErrorResponse(response);
                    break;
                default:
                    System.out.println("Received unexpected response to auth request");
            }
        }

        return isAuthed;
    }

    private static String GetFileNameFromUser() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter the filename you wish to request:");
        String result;
        result = br.readLine();

        return result;
    }

    private static Response.ControlResponse SendFileRequest(long userId,
                                                            String filename,
                                                            byte[] key,
                                                            DataOutputStream outStream,
                                                            DataInputStream inStream)
    {
        Request.ControlRequest.Builder builder = Request.ControlRequest.newBuilder();
        builder.setUserId(userId);
        builder.setControlCode(Request.RequestControlCode.REQUEST_FILE);
        builder.setFileName(filename);
        Request.ControlRequest fileRequest = builder.build();

        return PlaceRequest(fileRequest, key, outStream, inStream);
    }

    public static boolean HandleAuthResponse(Response.ControlResponse response)
    {
        boolean isAuthenticated = false;
        if(response.hasIsAuthenticated())
        {
            isAuthenticated = response.getIsAuthenticated();
        }
        else
        {
            System.out.println("Response did not indicate if the authentication was successful!");
        }

        return isAuthenticated;
    }

    public static void HandleErrorResponse(Response.ControlResponse response)
    {
        if(response.hasErrorMsg())
        {
            System.out.println("Error Response:");
            System.out.println(response.getErrorMsg());
        }
        else
        {
            System.out.println("Error response received but no message was provided!");
        }
    }

    public static void HandleFileResponse(Response.ControlResponse response)
    {
        if(response.hasFileName() && response.hasFileContents())
        {
            File outFile = new File(s_folderRoot + response.getFileName());
            File outDir = new File(outFile.getParent());
            if(!outDir.exists())
            {
                boolean mkdirs = outDir.mkdirs();
                if(!mkdirs)
                {
                    System.out.println("Failed to create the directories to store the file : " + response.getFileName());
                }
            }

            try(FileOutputStream fileOutputStream = new FileOutputStream(outFile))
            {
                fileOutputStream.write(response.getFileContents().toByteArray());
                System.out.println("Saved file to : " + outFile.getAbsolutePath());
            }
            catch (IOException ex)
            {
                System.out.println("Could not save received file!");
                System.out.println(ex.getMessage());
            }
        }
        else
        {
            System.out.println("File response does not contain filename or contents!");
        }
    }

    public static void HandleResponse(Response.ControlResponse controlResponse)
    {
        switch(controlResponse.getControlCode())
        {
            case RESPONSE_AUTH:
                HandleAuthResponse(controlResponse);
                break;
            case RESPONSE_ERROR:
                HandleErrorResponse(controlResponse);
                break;
            case RESPONSE_FILE:
                HandleFileResponse(controlResponse);
                break;
            default:
                System.out.println("Invalid response control code!");
        }
    }

    private static boolean QueryContinue() throws IOException
    {
        boolean hasResult = false;
        boolean shouldContinue = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(!hasResult)
        {
            System.out.println("Would you like to request another file? (Y or N)");
            String input = br.readLine().toLowerCase();
            if(input.length() > 0)
            {
                if(input.charAt(0) == 'y')
                {
                    hasResult = true;
                    shouldContinue = true;
                }
                else if(input.charAt(0) == 'n')
                {
                    hasResult = true;
                    shouldContinue = false;
                }
            }
            if(!hasResult)
            {
                System.out.println("Please enter in either a 'Y' or a 'N'");
            }
        }

        return shouldContinue;
    }

    private static void Run(Config config, DataOutputStream outStream, DataInputStream inStream, Socket socket) throws IOException
    {
        String fileName;

        do
        {
            fileName = GetFileNameFromUser();
            Response.ControlResponse fileResponse = SendFileRequest(config.userId, fileName, config.key, outStream, inStream);
            if(fileResponse != null)
            {
                HandleResponse(fileResponse);
            }
        }while(!socket.isClosed() && QueryContinue());

        if(socket.isClosed())
        {
            System.out.println("Disconnected from server");
        }
        else
        {
            socket.close();
        }
    }

    private static Config LoadConfig(String configFileName) throws FileNotFoundException
    {
        Config config = new Config();

        File configFile = new File(configFileName);
        FileInputStream fileInputStream = new FileInputStream(configFile);

        JsonParserFactory factory=JsonParserFactory.getInstance();
        JSONParser jsonParser = factory.newJsonParser();

        Map parsedJson;

        try
        {
            parsedJson = jsonParser.parseJson(fileInputStream, "UTF-8");
        }
        catch(JSONParsingException ex)
        {
            System.out.println("Failed to parse json config file!");
            System.out.println(ex.getMessage());
            return null;
        }

        // folder (optional)
        if(parsedJson.containsKey("folder"))
        {
            config.folderRoot = (String)parsedJson.get("folder");
        }

        // ipAddr (optional
        if(parsedJson.containsKey("ipAddr"))
        {
            config.serverAddress = (String)parsedJson.get("ipAddr");
        }

        // port (optional)
        if(parsedJson.containsKey("port"))
        {
            try
            {
                config.serverPort = Integer.parseInt((String)parsedJson.get("port"));
            }
            catch(NumberFormatException ex)
            {
                System.out.println("Failed to parse port, please make sure it is an int!");
                return null;
            }
        }

        // id (required)
        if(parsedJson.containsKey("id"))
        {
            try
            {
                config.userId = Integer.parseInt((String)parsedJson.get("id"));
            }
            catch(NumberFormatException ex)
            {
                System.out.println("Could not parse id, please ensure it is an integer!");
                return null;
            }
        }
        else
        {
            System.out.println("config file must contain an id!");
            return null;
        }

        // key (required)
        if(parsedJson.containsKey("key"))
        {
            config.key = ((String)parsedJson.get("key")).getBytes();
            if(config.key.length != Encryptor.GetKeySize())
            {
                System.out.println("Key is the wrong size!");
                System.out.println("Expected " + Encryptor.GetKeySize() + " bytes. Got " + config.key.length + " bytes.");
                return null;
            }
        }
        else
        {
            System.out.println("config file must contain a key!");
            return null;
        }

        return config;
    }

    public static void main(String[] args)
    {
        String osName = System.getProperty("os.name");
        if(osName.toLowerCase().contains("windows"))
        {
            File lib = new File("lib/libcom_jetucker_Encryptor.dll");
            System.load(lib.getAbsolutePath());
        }
        else
        {
            File lib = new File("lib/libcom_jetucker_Encryptor.so");
            System.load(lib.getAbsolutePath());
        }

        try
        {
            String configFile = "client.json";
            if(args.length > 0)
            {
                configFile = args[0];
            }

            Config config = LoadConfig(configFile);

            if(config == null)
            {
                System.out.println("Could not parse config file, please check the contents!");
                return;
            }

            s_folderRoot = config.folderRoot;
            File rootDir = new File(config.folderRoot);
            config.folderRoot = rootDir.getAbsolutePath() + "/";

            try(    Socket socket = new Socket(config.serverAddress,config.serverPort);
                    DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
                    DataInputStream inStream = new DataInputStream(socket.getInputStream()))
            {
                if(Authenticate(config.userId, config.key, outStream, inStream))
                {
                    Run(config, outStream, inStream, socket);
                }
                else
                {
                    System.out.println("Authentication Failed!");
                }
            }
        }
        catch(IOException ex)
        {
            System.out.println("IO error getting input : " + ex.getMessage());
        }
    }
}
