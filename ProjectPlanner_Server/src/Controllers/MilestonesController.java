package Controllers;

import Models.*;
import Server.Server;
import Utils.*;
import com.sun.rowset.CachedRowSetImpl;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javafx.util.Pair;
import javax.sql.rowset.CachedRowSet;

public class MilestonesController {
    
    private static final Connection conn = MySQLConnector.getConnection();

    public static void callGetById() throws IOException {
        int id = Server.receiveInt();
        
        getById(id);
    }
    
    public static void callGetAll() {
        getAll();
    }
    
    public static void callGetAllByProjectId() throws IOException, ParseException {
        int projectId = Server.receiveInt();
        
        getAllByProjectId(projectId);
    }
    
    public static void callCreate() throws IOException, ClassNotFoundException {
        Milestone milestone = (Milestone) Server.receiveObject();
        
        create(milestone);
    }
    
    public static void callUpdate() throws IOException, ClassNotFoundException {
        Milestone milestone = (Milestone) Server.receiveObject();
        
        update(milestone);
    }
    
    public static void callDeleteById() throws IOException {
        int id = Server.receiveInt();
        
        deleteById(id);
    }
    
    public static void callGetMilestoneStatusById() throws IOException {
        int id = Server.receiveInt();
        
        getMilestoneStatusById(id);
    }
    
    public static Milestone getById(int id) {
        
        Milestone milestone = null;
        
        try {
            String query = "SELECT * FROM milestones WHERE Id = (?)";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            CachedRowSet result = new CachedRowSetImpl();
            result.populate(resultSet);  
            
            Server.sendObject(result);
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return milestone;
    }
    
    public static List<Milestone> getAll() {
        
        List<Milestone> milestones = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM milestones";
            
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
        
        return milestones;
    }
    
    public static List<Milestone> getAllByProjectId(int projectId) {
        
        List<Milestone> milestones = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM milestones WHERE ProjectId = (?)";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, projectId);

            ResultSet resultSet = statement.executeQuery();
            CachedRowSet result = new CachedRowSetImpl();
            result.populate(resultSet);  
            
            Server.sendObject(result);
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return milestones;
    }
    
    
    public static Boolean create(Milestone milestone) throws IOException {
        
        try {
            String query = "INSERT INTO milestones " +
                            "(ProjectId, Title, StartDate, EndDate, Description) " +
                            "VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement statement = conn.prepareStatement(query);
            
            statement.setInt(1, milestone.getProjectId());
            statement.setString(2, milestone.getTitle());
            statement.setDate(3, new java.sql.Date(milestone.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(milestone.getEndDate().getTime()));
            statement.setString(5, milestone.getDescription());
            statement.executeUpdate();
           
 
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            Server.sendBoolean(false);
            return false;
        }
        
        Server.sendBoolean(true);
        return false;
    }
    
    public static Boolean update(Milestone milestone) throws IOException {
        
        try {
            String query = "UPDATE milestones " + 
                           "SET ProjectId = ?, Title = ?, StartDate = ?, EndDate = ?, Description = ? " +
                           "WHERE Id = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            
            statement.setInt(1, milestone.getProjectId());
            statement.setString(2, milestone.getTitle());
            statement.setDate(3, new java.sql.Date(milestone.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(milestone.getEndDate().getTime()));    
            statement.setString(5, milestone.getDescription());
            statement.setInt(6,milestone.getId());
            statement.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            Server.sendBoolean(false);
            return false;
        }
        
        Server.sendBoolean(false);
        return false;
    }
    
    public static Boolean deleteById(int id) throws IOException {
        try {
            String query = "DELETE FROM milestones WHERE Id = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            Server.sendBoolean(false);
            return false;
        }  
        
        Server.sendBoolean(true);
        return false;
    }
    
    //Number of finished tasks/Number of tasks
    public static Pair<Integer, Integer> getMilestoneStatusById(int id) throws IOException {
            
        Pair<Integer, Integer> resultPair = new Pair<Integer, Integer>(-1, -1);
        
        try {
            String query = "SELECT COUNT(t.Finished), " +
                    "SUM(CASE WHEN t.Finished = 1 THEN 1 ELSE 0 END) " +
                    "FROM milestones m JOIN tasks t ON t.MilestoneId = m.Id " + 
                    "WHERE m.Id = (?)";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                resultPair = new Pair<Integer, Integer>(result.getInt(1),
                                                        result.getInt(2));
            }
            
            statement.close();
            
            Server.sendObject(resultPair);
            return resultPair;
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            Server.sendObject(resultPair);
            return null;
        }    
    }
}
