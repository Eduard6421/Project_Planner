package Controllers;

import Client.Client;
import Models.*;
import Utils.*;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javafx.util.Pair;
import javax.sql.rowset.CachedRowSet;

public class MilestonesController {
    
    public static Milestone getById(int id) {
        
        Milestone milestone = null;
        
        try {
            Client.sendString("Milestones");
            Client.sendString("getById");
            Client.sendInt(id);
            
            CachedRowSet result = (CachedRowSet) Client.receiveObject();
            
            while (result.next()) {
                milestone = new Milestone(result.getInt("Id"),
                                        result.getInt("ProjectId"),
                                        result.getString("Title"),
                                        result.getDate("StartDate"),
                                        result.getDate("EndDate"),
                                        result.getString("Description"));
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return milestone;
    }
    
    public static List<Milestone> getAll() {
        
        List<Milestone> milestones = new ArrayList<>();
        
        try {
            Client.sendString("Milestones");
            Client.sendString("getAll");
            
            CachedRowSet result = (CachedRowSet) Client.receiveObject();
            
            while (result.next()) {
                 Milestone milestone = new Milestone(result.getInt("Id"),
                                                    result.getInt("ProjectId"),
                                                    result.getString("Title"),
                                                    result.getDate("StartDate"),
                                                    result.getDate("EndDate"),
                                                    result.getString("Description"));
                milestones.add(milestone);
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return milestones;
    }
    
    public static List<Milestone> getAllByProjectId(int projectId) {
        
        List<Milestone> milestones = new ArrayList<>();
        
        try {
            Client.sendString("Milestones");
            Client.sendString("getAllByProjectId");
            Client.sendInt(projectId);
            
            CachedRowSet result = (CachedRowSet) Client.receiveObject();
            
            while (result.next()) {
                 Milestone milestone = new Milestone(result.getInt("Id"),
                                                    result.getInt("ProjectId"),
                                                    result.getString("Title"),
                                                    result.getDate("StartDate"),
                                                    result.getDate("EndDate"),
                                                    result.getString("Description"));
                milestones.add(milestone);
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return milestones;
    }
    
    
    public static Milestone create(Milestone milestone) {
        
        try {
            Client.sendString("Milestones");
            Client.sendString("create");
            Client.sendObject(milestone);
            
            Boolean created = Client.receiveBoolean();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
        
        return milestone;
    }
    
    public static Milestone update(Milestone milestone) {
        
        try {
            Client.sendString("Milestones");
            Client.sendString("update");
            Client.sendObject(milestone);
            
            Boolean updated = Client.receiveBoolean();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
        
        return milestone;
    }
    
    public static void deleteById(int id) {
        try {
            Client.sendString("Milestones");
            Client.sendString("deleteById");
            Client.sendInt(id);
            
            Boolean deleted = Client.receiveBoolean();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }  
    }
    
    //Number of finished tasks/Number of tasks
    public static Pair<Integer, Integer> getMilestoneStatus(int id) {
        
        Pair<Integer, Integer> resultPair = null;
        
        try {
            Client.sendString("Milestones");
            Client.sendString("getMilestoneStatusById");
            Client.sendInt(id);
            
            resultPair = (Pair<Integer, Integer>) Client.receiveObject();
            
            return resultPair;
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }    
    }
}
