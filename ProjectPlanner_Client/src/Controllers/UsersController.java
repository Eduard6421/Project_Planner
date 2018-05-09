package Controllers;

import Models.*;
import Utils.*;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class UsersController {
    
    private static final Connection conn = MySQLConnector.getConnection();
    
    public static List<User> GetAll() {
        
        List<User> users = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM users";
            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while (result.next()) {
                User user = new User(result.getInt("Id"),
                                    result.getInt("RoleId"),
                                    result.getString("Username"),
                                    result.getString("Password"),
                                    result.getString("FirstName"),
                                    result.getString("LastName"));
                users.add(user);
            }
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return users;
    }
    
    public static List<User> GetManagers() {
        
        List<User> managers = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM users u JOIN roles r ON u.RoleId = r.Id " + 
                            "WHERE UPPER(r.Title) = 'MANAGER'";
            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while (result.next()) {
                User manager = new User(result.getInt("Id"),
                                    result.getInt("RoleId"),
                                    result.getString("Username"),
                                    result.getString("Password"),
                                    result.getString("FirstName"),
                                    result.getString("LastName"));
                managers.add(manager);
            }
            
            statement.close();          
        } 
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return managers;
    }
}
