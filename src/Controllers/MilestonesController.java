package Controllers;

import Models.*;
import Utils.*;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

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
            statement.setInt(6,milestone.getProjectId());
            statement.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
        
        return milestone;
    }
    
    public static void DeleteById(int id)
    {
        
        
        try {
            String query = "DELETE FROM milestones WHERE Id=? ";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
      
        
        
    }
}
