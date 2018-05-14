package Server;

import Controllers.*;
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
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;


public class Server {
    
    private static ServerSocket serverSocket = null;
    private static Socket socket = null;
    private static ObjectInputStream objectIS = null;
    private static ObjectOutputStream objectOS = null;
    
    private static int port = 1080; 
    
    private static Boolean isUp = false;
    
    
    public static void startServer() throws IOException, ParseException, ParseException, ClassNotFoundException, ClassNotFoundException {
        if (isUp == false) {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started!");
            socket = serverSocket.accept();
            
            objectOS = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            objectIS = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            System.out.println("Server started!");
            
            isUp = true;
            
            listen();
        }
        else {
            System.out.println("Server is up and running!");
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
    
    private static void listen() throws IOException, ParseException, ClassNotFoundException {
        for ( ; ; ) {
            String controller = receiveString();
            System.out.println(controller);
            String method = receiveString();
            System.out.println(method);
            
            switch (controller) {
                case "Auth":
                    if (method.equals("logIn")) {
                        AuthController.callLogIn();
                    }
                    break;
                    
                case "Projects":
                    switch(method) {
                        case "getById":
                            ProjectsController.callGetById();
                            break;
                        case "getAll":
                            ProjectsController.callGetAll();
                            break;
                        case "getAllBetweenDates":
                            ProjectsController.callGetAllBetweenDates();
                            break;
                        case "create":
                            ProjectsController.callCreate();
                            break;
                        case "update":
                            ProjectsController.callUpdate();
                            break;
                        case "deleteById":
                            ProjectsController.callDeleteById();
                            break;
                        case "getProjectStatusById":
                            ProjectsController.callGetProjectStatusById();
                            break;
                    }
                    break;
                case "Priorities":
                    if (method.equals("getAll")) {
                        PrioritiesController.callGetAll();
                    }
                    break;
                case "Milestones":
                    switch(method) {
                        case "getById":
                            MilestonesController.callGetById();
                            break;
                        case "getAll":
                            MilestonesController.callGetAll();
                            break;
                        case "getAllByProjectId":
                            MilestonesController.callGetAllByProjectId();
                            break;
                        case "create":
                            MilestonesController.callCreate();
                            break;
                        case "update":
                            MilestonesController.callUpdate();
                            break;
                        case "deleteById":
                            MilestonesController.callDeleteById();
                            break;
                        case "getMilestoneStatusById":
                            MilestonesController.callGetMilestoneStatusById();
                            break;
                    }
                    break;
            }
        }
    }
}