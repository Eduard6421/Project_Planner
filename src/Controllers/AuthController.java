package Controllers;

import Utils.*;
import java.sql.*;

public class AuthController {

    private static Connection conn = MySQLConnector.getConnection();

    public void LogIn(String username, String password) {
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
                    break;
                } 
                
                GlobalData.setUsername(result.getString("u.Username"));
                GlobalData.setUserId(result.getInt("u.Id"));
                GlobalData.setRoleTitle(result.getString("u.Title"));
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
