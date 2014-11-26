package com.jetucker;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jesse on 2014-11-25.
 */
public final class Server
{
    private static int s_portNumber = 16000;
    private static String s_folderRoot = "./";

    private static HashMap<Long, Long> s_userIdToKey;
    private static HashMap<Long, RequestHandler> s_authenticatedUsers = new HashMap<>();

    static
    {
        s_userIdToKey = new HashMap<>();
        s_userIdToKey.put(1L, 1L);
        s_userIdToKey.put(2L, 2L);
        s_userIdToKey.put(3L, 3L);
        s_userIdToKey.put(4L, 4L);
    }

    private static final class RequestHandler implements Runnable
    {

        private Socket m_socket;
        private Long m_key;
        private Long m_userId;

        RequestHandler(Socket soc)
        {
            m_socket = soc;
        }

        private Response.ControlResponse CreateAuthResponse(long userId, boolean isAuthed)
        {
            Response.ControlResponse.Builder builder = Response.ControlResponse.newBuilder();
            builder.setControlCode(Response.ResponseControlCode.RESPONSE_AUTH);
            builder.setUserId(userId);
            builder.setIsAuthenticated(isAuthed);
            return builder.build();
        }

        private Response.ControlResponse CreateErrorResponse(long userId, String errorMsg)
        {
            Response.ControlResponse.Builder builder = Response.ControlResponse.newBuilder();
            builder.setControlCode(Response.ResponseControlCode.RESPONSE_ERROR);
            builder.setUserId(userId);
            builder.setErrorMsg(errorMsg);

            return builder.build();
        }

        private Response.ControlResponse CreateFileResponse(long userId, String filename, ByteString bytes)
        {
            Response.ControlResponse.Builder builder = Response.ControlResponse.newBuilder();
            builder.setControlCode(Response.ResponseControlCode.RESPONSE_FILE);
            builder.setUserId(userId);
            builder.setFileName(filename);
            builder.setFileContents(bytes);

            return builder.build();
        }

        private Response.ControlResponse HandleAuthRequest(Request.ControlRequest request)
        {
            boolean isAuthSuccessful = false;

            if(s_userIdToKey.containsKey(request.getUserId()))
            {
                synchronized (s_authenticatedUsers)
                {
                    long userId = request.getUserId();
                    if(!s_authenticatedUsers.containsKey(userId))
                    {
                        isAuthSuccessful = true;
                        s_authenticatedUsers.put(userId, this);
                    }
                }
            }

            return CreateAuthResponse(request.getUserId(), isAuthSuccessful);
        }

        private Response.ControlResponse VerifyAuthed(Request.ControlRequest request)
        {
            boolean isAuthed = false;

            synchronized (s_authenticatedUsers)
            {
                long userId = request.getUserId();
                if(s_authenticatedUsers.containsKey(userId))
                {
                    isAuthed = s_authenticatedUsers.get(userId) == this;
                }
            }

            Response.ControlResponse errorResponse = null;

            if(!isAuthed)
            {
                errorResponse = CreateErrorResponse(request.getUserId(), "User is not authenticated!");
            }

            return errorResponse;
        }


        private Response.ControlResponse HandleFileRequest(Request.ControlRequest request)
        {
            // can only request a file if the user is authenticated
            Response.ControlResponse response = VerifyAuthed(request);

            if(response == null)
            {
                String errorMsg = null;
                // good, the user can actually request a file
                try
                {
                    File file = new File(s_folderRoot + request.getFileName());
                    FileInputStream inStream = new FileInputStream(file);
                    long fileLength = file.length();
                    if(fileLength < Integer.MAX_VALUE)
                    {
                        ByteString fileBytes = ByteString.readFrom(inStream);
                        response = CreateFileResponse(request.getUserId(), request.getFileName(), fileBytes);
                    }
                    else
                    {
                        errorMsg = "Server does not allow for files that exceed " + Integer.MAX_VALUE + " bytes!";
                    }
                }
                catch (FileNotFoundException ex)
                {
                    errorMsg = "File not found";
                }
                catch (IOException ex)
                {
                    errorMsg = "An exception occurred while reading the file";
                }

                if(errorMsg != null)
                {
                    response = CreateErrorResponse(request.getUserId(), errorMsg);
                }
            }

            return response;
        }

        private Response.ControlResponse HandleFinishRequest(Request.ControlRequest request)
        {
            Response.ControlResponse response = VerifyAuthed(request);

            if(response != null)
            {
                synchronized (s_authenticatedUsers)
                {
                    s_authenticatedUsers.remove(request.getUserId());
                    try
                    {
                        m_socket.close();
                    }
                    catch (IOException e)
                    {
                        System.out.println("Failed to close socket!");
                    }
                    response = CreateAuthResponse(request.getUserId(), false);
                }
            }

            return response;
        }

        private Response.ControlResponse HandleRequest(Request.ControlRequest request)
        {
            Response.ControlResponse response = null;
            switch(request.getControlCode())
            {
                case REQUEST_AUTH:
                    response = HandleAuthRequest(request);
                    break;
                case REQUEST_FILE:
                    response = HandleFileRequest(request);
                    break;
                case REQUEST_FINISH:
                    response = HandleFinishRequest(request);
                    break;
                default:
                    System.out.println("Encountered unknown request!");
            }

            return response;
        }

        private void SendResponse(Response.ControlResponse response, long key, DataOutputStream outStream) throws IOException
        {
            Encryptor encryptor = new Encryptor();
            byte[] responseBytes = response.toByteArray();
            byte[] encryptedResponse = encryptor.Encrypt(responseBytes, key);
            outStream.writeInt(encryptedResponse.length);
            outStream.write(encryptedResponse);
            outStream.flush();
        }

        private Request.ControlRequest DecodeRequest(byte[] encryptedRequest)
        {
            Request.ControlRequest request = null;
            Encryptor encryptor = new Encryptor();
            byte[] decryptedRequest = null;

            if(m_key == null)
            {
                for(Map.Entry<Long, Long> entry : s_userIdToKey.entrySet())
                {
                    long userId = entry.getKey();
                    long key = entry.getValue();

                    decryptedRequest = encryptor.Decrypt(encryptedRequest, key);
                    try
                    {
                        request = Request.ControlRequest.parseFrom(decryptedRequest);
                        if(request.hasUserId() && request.getUserId() == userId)
                        {
                            m_userId = userId;
                            m_key = key;
                            break;
                        }
                        else
                        {
                            request = null;
                        }
                    }
                    catch(InvalidProtocolBufferException ex)
                    {
                        // just keep trying
                        System.out.println("Failed to decode request from client!");
                    }
                }
            }

            if(m_key != null)
            {
                if(decryptedRequest == null)
                {
                    decryptedRequest = encryptor.Decrypt(encryptedRequest, m_key);
                }

                if(request == null)
                {
                    try
                    {
                        request = Request.ControlRequest.parseFrom(decryptedRequest);
                        if(!(request.hasUserId() && request.getUserId() == m_userId))
                        {
                            request = null;
                        }
                    }
                    catch (InvalidProtocolBufferException ex)
                    {
                        // do nothing
                    }
                }
            }

            return request;
        }

        @Override
        public void run()
        {
            try( DataOutputStream outStream = new DataOutputStream(m_socket.getOutputStream());
                 DataInputStream inStream = new DataInputStream(m_socket.getInputStream()))
            {
                while(!m_socket.isClosed())
                {
                    int msgSize = inStream.readInt();
                    byte[] encryptedRequest = new byte[msgSize];
                    inStream.readFully(encryptedRequest);

                    Request.ControlRequest request = DecodeRequest(encryptedRequest);
                    if(request != null)
                    {
                        Response.ControlResponse response = HandleRequest(request);
                        SendResponse(response, m_key, outStream);
                    }
                    else
                    {
                        m_socket.close();
                    }
                }
            }
            catch (IOException ex)
            {
                System.out.println("Error handling request : ");
                System.out.println(ex.getMessage());
            }

            if(m_userId != null)
            {
                synchronized (s_authenticatedUsers)
                {
                    s_authenticatedUsers.remove(m_userId);
                }
            }
        }
    } // RequestHandler

    private static void RunServer()
    {
        try (ServerSocket serverSocket = new ServerSocket(s_portNumber))
        {
            while (true)
            {
                Socket soc = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(soc);
                new Thread(requestHandler).start();
            }
        }
        catch (IOException ex)
        {
            System.out.println("Fatal Error : ");
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args)
    {
        if(args.length > 0)
        {
            s_folderRoot = args[0];
        }

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

        RunServer();
    }
}
