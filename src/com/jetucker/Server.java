package com.jetucker;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Jesse on 2014-11-25.
 */
public final class Server
{
    private static int s_portNumber = 16000;
    private static String s_folderRoot = "./";

    private static final class RequestHandler implements Runnable
    {

        private Socket m_socket;
        RequestHandler(Socket soc)
        {
            m_socket = soc;
        }

        @Override
        public void run()
        {
            try( DataOutputStream outStream = new DataOutputStream(m_socket.getOutputStream());
                 DataInputStream inStream = new DataInputStream(m_socket.getInputStream()))
            {
                // TODO::JT
            }
            catch (IOException ex)
            {
                System.out.println("Error handling request : ");
                System.out.println(ex.getMessage());
            }

            try
            {
                m_socket.close();
            }
            catch (IOException e)
            {
                System.out.println("Failed to close socket!");
            }
        }
    }

    public static void RunServer()
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


    }
}
