package old_package;

import Controllers.LoginController;
import Utils.*;
import GUIPackage.LoginMenu;
import java.sql.*;

public class Project_Planner {

    static Controller main_control;
    

    
    public static void startConnection(){
        
    MySQLConnector.getConnection();
      
    }
        
        
     
    
    
    public static void main(String[] args) {

       startConnection();
       
       LoginController control = new LoginController();
       

    }

}
