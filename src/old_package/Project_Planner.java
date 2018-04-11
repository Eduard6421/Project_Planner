package old_package;

import Controllers.LoginMenuController;
import Utils.*;

public class Project_Planner {

    static Controller main_control;
    

    
    public static void startConnection(){
        
    MySQLConnector.getConnection();
      
    }
        
        
     
    
    
    public static void main(String[] args) {


        LoginMenuController controller = new LoginMenuController();

    }

}
