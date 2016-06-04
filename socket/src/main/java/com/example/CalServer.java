package com.example;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Created by JasonChang on 2016/6/4.
 */
public class CalServer implements Runnable{

    private Thread thread;
    private ServerSocket servSock;

    public CalServer()
    {
        try {
            // Detect server ip
            InetAddress IP = InetAddress.getLocalHost();
            System.out.println("IP of my system is := "+IP.getHostAddress());
            System.out.println("Waitting to connect......");

            // Create server socket
            servSock = new ServerSocket(2000);

            // Create socket thread
            thread = new Thread(this);
            thread.start();
        } catch (java.io.IOException e) {
            System.out.println("Socket啟動有問題 !");
            System.out.println("IOException :" + e.toString());
        } finally{

        }
    }

    @Override
    public void run(){
        // Running for waitting multiple client
        while(true){
            try{
                // After client connected, create client socket connect with client
                Socket clntSock = servSock.accept();
                InputStream in = clntSock.getInputStream();

                System.out.println("Connected!!");

                // Transfer data
                byte[] b = new byte[1024];
                int length;

                length = in.read(b);
                String s = new String(b);
                System.out.println("[Server Said]" + s);

            }
            catch(Exception e){
                //System.out.println("Error: "+e.getMessage());
            }
        }
    }

    public static void main(String[] args)
    {
        CalServer server = new CalServer();
    }

}
