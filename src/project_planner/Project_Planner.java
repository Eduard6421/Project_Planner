/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_planner;

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
       

       
       return main_control.isConnected();
       
    
  
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        //LoginMenu login = new LoginMenu();
        //login.setVisible(true);
        
        StartConnection("admin","admin");
    
    }
    
}
