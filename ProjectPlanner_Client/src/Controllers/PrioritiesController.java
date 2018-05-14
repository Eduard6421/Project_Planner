package Controllers;

import Client.Client;
import Models.*;
import Utils.*;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;

public class PrioritiesController {
    
    public static List<Priority> GetAll() {
        
        List<Priority> priorities = new ArrayList<>();
        
        try {
            Client.sendString("Priorities");
            Client.sendString("getAll");
            
            CachedRowSet result = (CachedRowSet) Client.receiveObject();
            
            while (result.next()) {
                Priority priority = new Priority(result.getInt("Id"),
                                                result.getString("Title"));
                priorities.add(priority);
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return priorities;
    }
}
