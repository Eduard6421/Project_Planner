package Controllers;

import Server.Server;
import Utils.*;
import java.io.IOException;
import java.sql.*;

public class AuthController {

    private static final Connection conn = MySQLConnector.getConnection();
    
    public static void callLogIn() throws IOException, ClassNotFoundException {
        String username = Server.receiveString();
        String password = Server.receiveString();
        
        System.out.println(username);
        System.out.println(password);
        logIn(username, password);
    }

    public static boolean logIn(String username, String password) throws IOException {
        try {
            String query = "SELECT COUNT(1), u.Username, u.Id, r.Title " +
                    "FROM users u INNER JOIN roles r ON r.Id = u.RoleId " +
                    "WHERE Username = (?) and Password = (?)";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                
                if (!result.getBoolean("COUNT(1)")) {
                    System.out.println("No such user exists!");
                    Server.sendBoolean(false);
                    return false;
                } 
                
                Server.sendBoolean(true);

                Server.sendString(result.getString("u.Username"));
                Server.sendInt(result.getInt("u.Id"));
                Server.sendString(result.getString("r.Title"));
            }
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            Server.sendBoolean(false);
            return false;
        }
        
        return true;
    }
}
