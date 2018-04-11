package old_package;

import Controllers.LoginMenuController;
import Controllers.ProjectsMenuController;
import Utils.*;
import GUIPackage.LoginMenu;
import java.sql.*;

public class Project_Planner {

    static Controller main_control;
    

    
    public static void startConnection(){
        
    MySQLConnector.getConnection();
      
    }
        
        
     
    
    
    public static void main(String[] args) {


        ProjectsMenuController controller = new ProjectsMenuController();
        
        
       

    }

}
