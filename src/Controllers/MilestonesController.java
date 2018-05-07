package Controllers;

import Models.*;
import Utils.*;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javafx.util.Pair;

public class MilestonesController {
    
    private static final Connection conn = MySQLConnector.getConnection();
    
    public static Milestone GetById(int id) {
        
        Milestone milestone = null;
        
        try {
            String query = "SELECT * FROM milestones WHERE Id = (?)";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                milestone = new Milestone(result.getInt("Id"),
                                        result.getInt("ProjectId"),
                                        result.getString("Title"),
                                        result.getDate("StartDate"),
                                        result.getDate("EndDate"),
                                        result.getString("Description"));
            }
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return milestone;
    }
    
    public static List<Milestone> GetAll() {
        
        List<Milestone> milestones = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM milestones";
            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while (result.next()) {
                 Milestone milestone = new Milestone(result.getInt("Id"),
                                                    result.getInt("ProjectId"),
                                                    result.getString("Title"),
                                                    result.getDate("StartDate"),
                                                    result.getDate("EndDate"),
                                                    result.getString("Description"));
                milestones.add(milestone);
            }
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return milestones;
    }
    
        public static List<Milestone> GetAllByProjectId(int projectId) {
        
        List<Milestone> milestones = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM milestones WHERE ProjectId = (?)";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, projectId);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                 Milestone milestone = new Milestone(result.getInt("Id"),
                                                    result.getInt("ProjectId"),
                                                    result.getString("Title"),
                                                    result.getDate("StartDate"),
                                                    result.getDate("EndDate"),
                                                    result.getString("Description"));
                milestones.add(milestone);
            }
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return milestones;
    }
    
    
    public static Milestone Create(Milestone milestone) {
        
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
            return null;
        }
        
        return milestone;
    }
    
    public static Milestone Update(Milestone milestone) {
        
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
            return null;
        }
        
        return milestone;
    }
    
    public static void DeleteById(int id) {
        try {
            String query = "DELETE FROM milestones WHERE Id= ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }  
    }
    
    //Number of finished tasks/Number of tasks
    public static Pair<Integer, Integer> GetMilestoneStatus(int id) {
        try {
            String query = "SELECT COUNT(t.Finished), " +
                    "SUM(CASE WHEN t.Finished = 1 THEN 1 ELSE 0 END) " +
                    "FROM milestomes m JOIN tasks t ON t.MilestonId = m " + 
                    "WHERE m.Id = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            
            ResultSet result = statement.executeQuery();
            
            Pair<Integer, Integer> resultPair = null;
            
            while (result.next()) {
                resultPair = new Pair<Integer, Integer>(result.getInt(1),
                                                        result.getInt(2));
            }
            
            statement.close();
            
            return resultPair;
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }    
    }
}
