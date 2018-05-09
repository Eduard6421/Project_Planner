package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Client {
    
    private static Socket socket = null;
    
    private static int port = 1080;
    private static String address = "localhost";
    
    private static DataInputStream dataIS; 
    private static DataOutputStream dataOS;
    
    private static Boolean isUp = false;
    
    
    public static void startClient() throws IOException {
        if (isUp == false) {        
            System.out.println("Client started!");
            socket = new Socket(address, port);
        
            dataIS = new DataInputStream(socket.getInputStream());
            dataOS = new DataOutputStream(socket.getOutputStream());
            
            isUp = true;
        }
        else {
            System.out.println("Client is up and running!");
        }
    }
}
