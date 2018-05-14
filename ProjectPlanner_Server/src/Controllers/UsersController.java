package Controllers;

import Models.*;
import Server.Server;
import Utils.*;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;

public class UsersController {
    
    private static final Connection conn = MySQLConnector.getConnection();
    
    public static void callGetAll() {
        getAll();
    }
    
    public static void callGetManagers() {
        getManagers();
    }
    
    public static List<User> getAll() {
        
        List<User> users = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM users";
            
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            CachedRowSet result = new CachedRowSetImpl();
            result.populate(resultSet);
            
            Server.sendObject(result);
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return users;
    }
    
    public static List<User> getManagers() {
        
        List<User> managers = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM users u JOIN roles r ON u.RoleId = r.Id " + 
                            "WHERE UPPER(r.Title) = 'MANAGER'";
            
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            CachedRowSet result = new CachedRowSetImpl();
            result.populate(resultSet);
            
            Server.sendObject(result);
            
            statement.close();          
        } 
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return managers;
    }
}
