package Controllers;

import Client.Client;
import Models.*;
import Utils.*;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;

public class UsersController {
    
    public static List<User> GetAll() {
        
        List<User> users = new ArrayList<>();
        
        try {
            Client.sendString("Users");
            Client.sendString("getAll");
            
            CachedRowSet result = (CachedRowSet) Client.receiveObject();
            
            while (result.next()) {
                User user = new User(result.getInt("Id"),
                                    result.getInt("RoleId"),
                                    result.getString("Username"),
                                    result.getString("Password"),
                                    result.getString("FirstName"),
                                    result.getString("LastName"));
                users.add(user);
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return users;
    }
    
    public static List<User> GetManagers() {
        
        List<User> managers = new ArrayList<>();
        
        try {
            Client.sendString("Users");
            Client.sendString("getManagers");
            
            CachedRowSet result = (CachedRowSet) Client.receiveObject();
            
            while (result.next()) {
                User manager = new User(result.getInt("Id"),
                                    result.getInt("RoleId"),
                                    result.getString("Username"),
                                    result.getString("Password"),
                                    result.getString("FirstName"),
                                    result.getString("LastName"));
                managers.add(manager);
            }        
        } 
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return managers;
    }
}
