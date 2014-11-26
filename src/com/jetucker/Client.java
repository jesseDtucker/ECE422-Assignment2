package com.jetucker;

import com.google.protobuf.InvalidProtocolBufferException;

import java.io.*;
import java.net.Socket;

public final class Client
{
    private static String s_folderRoot = "./";
    private static String s_serverAddress = "127.0.0.1";
    private static int s_serverPort = 16000;

    private static long GetLongFromUser(String prompt) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        boolean hasId = false;
        int userId = -1;

        System.out.println(prompt);
        while(!hasId && (input = br.readLine()) != null)
        {
            try
            {
                userId = Integer.parseInt(input);
                hasId = true;
            }
            catch (NumberFormatException ex)
            {
                System.out.println("Please enter a valid integer!");
            }
        }

        return userId;
    }

    private static Response.ControlResponse PlaceRequest(Request.ControlRequest request,
                                                         long key,
                                                         DataOutputStream outputStream,
                                                         DataInputStream inputStream)
    {
        Response.ControlResponse response = null;
        // encrypt message
        Encryptor encryptor = new Encryptor();
        byte[] unencryptedRequest = request.toByteArray();
        byte[] encryptedRequest = encryptor.Encrypt(unencryptedRequest, key);

        System.out.println("Sending request : ");
        System.out.println(request.toString());
        System.out.println("Request : ");
        for(byte b : unencryptedRequest)
        {
            System.out.print(b + " ");
        }
        System.out.println("\nEncrypted Request : ");
        for(byte b : encryptedRequest)
        {
            System.out.print(b + " ");
        }
        System.out.println();

        // send message
        try
        {
            outputStream.writeInt(encryptedRequest.length);
            outputStream.write(encryptedRequest);
            outputStream.flush();

            // get response
            int msgSize = inputStream.readInt();
            byte[] encryptedResponse = new byte[msgSize];
            inputStream.readFully(encryptedResponse);

            // decrypt response
            byte[] rawResponse = encryptor.Decrypt(encryptedResponse, key);
            try
            {
                response = Response.ControlResponse.parseFrom(rawResponse);
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

    private static boolean Authenticate(long userId, long key, DataOutputStream outStream, DataInputStream inStream)
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
                                                            long key,
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
            new File(outFile.getParent()).mkdirs();
            try(FileOutputStream fileOutputStream = new FileOutputStream(outFile))
            {
                fileOutputStream.write(response.getFileContents().toByteArray());
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

    private static void Run(long userId, long key, DataOutputStream outStream, DataInputStream inStream) throws IOException
    {
        String fileName;

        do
        {
            fileName = GetFileNameFromUser();
            Response.ControlResponse fileResponse = SendFileRequest(userId, fileName, key, outStream, inStream);
            if(fileResponse != null)
            {
                HandleResponse(fileResponse);
            }
        }while(QueryContinue());
    }

    public static void main(String[] args)
    {
        try
        {
            if(args.length > 0)
            {
                s_folderRoot = args[0];
            }

            File rootDir = new File(s_folderRoot);
            s_folderRoot = rootDir.getAbsolutePath() + "/";

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

            long userId = GetLongFromUser("Please enter your ID:");
            long key = GetLongFromUser("Please enter your Key:");

            try(    Socket socket = new Socket(s_serverAddress,s_serverPort);
                    DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
                    DataInputStream inStream = new DataInputStream(socket.getInputStream()))
            {
                if(Authenticate(userId, key, outStream, inStream))
                {
                    Run(userId, key, outStream, inStream);
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
