package Client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client {
    
    private static Socket socket = null;
    
    private static int port = 1080;
    private static String address = "localhost";
    
    private static DataInputStream dataIS = null; 
    private static DataOutputStream dataOS = null;
    private static ObjectInputStream objectIS = null;
    private static ObjectOutputStream objectOS = null;
    
    private static Boolean isUp = false;
    
    
    public static void startClient() throws IOException {
        if (isUp == false) {        
            System.out.println("Client started!");
            socket = new Socket(address, port);
        
            dataIS = new DataInputStream(socket.getInputStream());
            dataOS = new DataOutputStream(socket.getOutputStream());
            objectIS = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            objectOS = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            
            isUp = true;
        }
        else {
            System.out.println("Client is up and running!");
        }
    }
}
