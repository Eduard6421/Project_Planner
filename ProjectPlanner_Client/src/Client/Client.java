package Client;

import Utils.Converters;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.util.Date;


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
    
    public static void sendObject(Object object) throws IOException {  
        objectOS.writeObject(object);
    }
    
    public static Object receiveObject() throws IOException, ClassNotFoundException {
        Object object = objectIS.readObject();
        
        return object;
    }
    
    public static void sendString(String string) throws IOException {
        dataOS.writeUTF(string);
    }
    
    public static String receiveString() throws IOException {
        String string = dataIS.readUTF();
        
        return string;
    }
    
    public static void sendInt(int number) throws IOException {
        String string = Converters.intToString(number);
        
        dataOS.writeUTF(string);
    }
    
    public static int receiveInt() throws IOException {
        int number = Converters.stringToInt(dataIS.readUTF());
        
        return number;
    }
    
    public static void sendDate(Date date) throws IOException {
        String string = Converters.dateToString(date);
        
        dataOS.writeUTF(string);
    }
    
    public static Date receiveDate() throws IOException, ParseException {
        Date date = Converters.stringToDate(dataIS.readUTF());
        
        return date;
    } 

    public static void sendBoolean(Boolean bool) throws IOException {
        String string = Converters.booleanToString(bool);
        
        dataOS.writeUTF(string);
    }
    
    public static Boolean receiveBoolean() throws IOException, ParseException {
        Boolean bool = Converters.stringToBoolean(dataIS.readUTF());
        
        return bool;
    } 
}
