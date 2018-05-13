package Client;

import Utils.Converters;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.util.Date;


public class Client {
    
    private static Socket socket = null;
    
    private static int port = 1080;
    private static String address = "localhost";
    
    private static ObjectInputStream objectIS = null;
    private static ObjectOutputStream objectOS = null;
    
    private static Boolean isUp = false;
    
    
    public static void startClient() throws IOException {
        if (isUp == false) {        
            System.out.println("Client started!");
            socket = new Socket(address, port);
            
            objectOS = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            objectOS.flush();
            objectIS = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            
            isUp = true;
        }
        else {
            System.out.println("Client is up and running!");
        }
    }
    
    public static void sendObject(Object object) throws IOException {  
       objectOS.writeObject(object);
       objectOS.flush();
    }
    
    public static Object receiveObject() throws IOException, ClassNotFoundException {
        Object object = objectIS.readObject();
        
        return object;
    }
    
    public static void sendString(String string) throws IOException {
        objectOS.writeUTF(string);
        objectOS.flush();
    }
    
    public static String receiveString() throws IOException, ClassNotFoundException {
        String string = objectIS.readUTF();
        
        return string;
    }
    
    public static void sendInt(int number) throws IOException {
        objectOS.writeInt(number);
        objectOS.flush();
    }
    
    public static int receiveInt() throws IOException {
        int number = objectIS.readInt();
        
        return number;
    }
    
    public static void sendDate(Date date) throws IOException {
        String string = Converters.dateToString(date);
        
        objectOS.writeUTF(string);
        objectOS.flush();
    }
    
    public static Date receiveDate() throws IOException, ParseException {
        Date date = Converters.stringToDate(objectIS.readUTF());
        
        return date;
    } 

    public static void sendBoolean(Boolean bool) throws IOException {  
        objectOS.writeBoolean(bool);
        objectOS.flush();
    }
    
    public static Boolean receiveBoolean() throws IOException, ParseException {
        Boolean bool = objectIS.readBoolean();
        
        return bool;
    } 
}
