package com.jetucker;

import com.google.protobuf.InvalidProtocolBufferException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    private static int GetUserId() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        boolean hasId = false;
        int userId = -1;

        while((input = br.readLine()) != null && !hasId)
        {
            try
            {
                userId = Integer.parseInt(input);
                hasId = true;
            }
            catch (NumberFormatException ex)
            {
                System.out.println("Please enter a valid integer ID!");
            }
        }

        return userId;
    }

    private static boolean Authenticate(int userId)
    {
        // TODO::JT
        return true;
    }

    private static String GetFileNameFromUser() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String result;
        result = br.readLine();

        return result;
    }

    private static byte[] BuildFileRequest(int userId, String filename)
    {
        Request.ControlRequest.Builder builder = Request.ControlRequest.newBuilder();
        builder.setUserId(userId);
        builder.setControlCode(Request.RequestControlCode.REQUEST_FILE);
        builder.setFileName(filename);
        Request.ControlRequest msg = builder.build();

        return msg.toByteArray();
    }

    private static byte[] RequestFile(byte[] request)
    {
        // Send request to server
        // wait for response
        // return response
        return null;
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

    }

    public static void HandleResponse(byte[] response)
    {
        try
        {
            Response.ControlResponse controlResponse = Response.ControlResponse.parseFrom(response);
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
        catch (InvalidProtocolBufferException ex)
        {
            System.out.println("Failed to decode server response!");
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

    private static void Run(int userId, long key) throws IOException
    {
        Encryptor encryptor = new Encryptor();
        String fileName;
        do
        {
            fileName = GetFileNameFromUser();
            byte[] request = BuildFileRequest(userId, fileName);
            byte[] encryptedRequest = encryptor.Encrypt(request, key);
            byte[] response = RequestFile(encryptedRequest);
            byte[] decryptedResponse = encryptor.Decrypt(response, key);
            HandleResponse(decryptedResponse);
        }while(QueryContinue());
    }

    public static void main(String[] args)
    {
        try
        {
            int userId = GetUserId();
            if(Authenticate(userId))
            {
                Run(userId, 0);
            }
            else
            {
                System.out.println("Authentication Failed!");
            }
        }
        catch(IOException ex)
        {
            System.out.println("IO error getting input : " + ex.getMessage());
        }
    }
}
