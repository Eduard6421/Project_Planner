package Controllers;

import Models.*;
import Utils.*;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class PrioritiesController {
    
    private static final Connection conn = MySQLConnector.getConnection();
    
    public static List<Priority> GetAll() {
        
        List<Priority> priorities = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM priorities";
            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while (result.next()) {
                Priority priority = new Priority(result.getInt("Id"),
                                                result.getString("Title"));
                priorities.add(priority);
            }
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return priorities;
    }
}
