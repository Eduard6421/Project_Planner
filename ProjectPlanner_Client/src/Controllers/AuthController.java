package Controllers;

import Client.Client;
import Utils.*;

public class AuthController {

    public static boolean logIn(String username, String password) {
        try {
            
            Client.sendString("Auth");
            Client.sendString("logIn");
            Client.sendString(username);
            Client.sendString(password);
            
            Thread.sleep(1000);
            
            GlobalData.setLoggedIn(Client.receiveBoolean());
            
            if (!GlobalData.isLoggedIn()) {
                System.out.println("Log in failed!");
                return false;
            }            
            
            GlobalData.setUsername(Client.receiveString());
            GlobalData.setUserId(Client.receiveInt());
            GlobalData.setRoleTitle(Client.receiveString());
            
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
        
        return true;
    }
    
   
    public void LogOut(){
        GlobalData.setLoggedIn(false);
        GlobalData.setRoleTitle(null);
        GlobalData.setUserId(0);
        GlobalData.setUsername(null);
    }
}
