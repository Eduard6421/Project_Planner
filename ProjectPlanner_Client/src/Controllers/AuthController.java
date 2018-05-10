package Controllers;

import Client.Client;
import Utils.*;
import java.sql.*;

public class AuthController {

    private static final Connection conn = MySQLConnector.getConnection();

    public static boolean LogIn(String username, String password) {
        try {
            String query = "SELECT COUNT(1), u.Username, u.Id, r.Title " +
                    "FROM users u INNER JOIN roles r ON r.Id = u.RoleId " +
                    "WHERE Username = (?) and Password = (?)";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                GlobalData.setLoggedIn(result.getBoolean("COUNT(1)"));
                
                if (!GlobalData.isLoggedIn()) {
                    System.out.println("No such user exists!");
                    return false;
                } 
                
                GlobalData.setUsername(result.getString("u.Username"));
                GlobalData.setUserId(result.getInt("u.Id"));
                GlobalData.setRoleTitle(result.getString("r.Title"));
            }
            
            statement.close();
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
