package Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {
    
    private static ServerSocket serverSocket = null;
    private static Socket socket = null;
    private static ObjectInputStream objectIS = null;
    private static ObjectOutputStream objectOS = null;
    
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
            objectIS = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            objectOS = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            
            isUp = true;
        }
        else {
            System.out.println("Server is up and running!");
        }
    }
}