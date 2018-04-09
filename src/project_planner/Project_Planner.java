/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_planner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Eduard
 */
public class Project_Planner {

    static Controller main_control;
    
    public static boolean StartConnection(String Username,String Password)
    {
       System.out.println(Username + " " + Password);
       
       
       main_control = new Controller(Username,Password);
       
       Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
       Date asd = myCalendar.getTime();
 
       myCalendar = new GregorianCalendar(2014, 4, 20);
       Date asd1 = myCalendar.getTime();
       
       main_control.connection.Update_Task("test",1,"new_test",3,"new_descirpt",asd1,2);
       
       return main_control.isConnected();
       
    
  
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        //LoginMenu login = new LoginMenu();
        //login.setVisible(true);
        

        StartConnection("admin","admin");
        
        
    
    }
    
}
