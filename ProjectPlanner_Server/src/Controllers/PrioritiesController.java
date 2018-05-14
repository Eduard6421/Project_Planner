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

public class PrioritiesController {
    
    private static final Connection conn = MySQLConnector.getConnection();
    
    public static void callGetAll() {
        getAll();
    }
    
    public static List<Priority> getAll() {
        
        List<Priority> priorities = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM priorities";
            
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
        
        return priorities;
    }
}
