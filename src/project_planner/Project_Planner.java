/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_planner;


import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author Eduard
 */
public class Project_Planner {


    public static void StartSoftware()
    {
        String command;
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Type 'connect' to log into database\n");    
       
        command = scanner.nextLine();
        
        if("connect".equals(command))
            StartConnection();
        

       
    }
    
    
    public static void StartConnection()
    {
        DBConnector connection = new DBConnector();
        
        connection.Log_in("admin","admin");
        
        Date now = new Date();
        Date next = new Date();
        connection.Create_Project("asd","asd",now,next,10000.2);
        
        
    }
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here

        StartSoftware();
            
        
    }
    
}
