package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {
    
    private static ServerSocket serverSocket = null;
    private static Socket socket = null;
    
    private static int port = 1080; 
    
    private static DataInputStream dataIS; 
    private static DataOutputStream dataOS;
    
    private static Boolean isUp = false;
    
    
    public static void startServer() throws IOException {
        if (isUp == false) {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started!");
            socket = serverSocket.accept();
        
            dataIS = new DataInputStream(socket.getInputStream());
            dataOS = new DataOutputStream(socket.getOutputStream());
            
            isUp = true;
        }
        else {
            System.out.println("Server is up and running!");
        }
    }
}