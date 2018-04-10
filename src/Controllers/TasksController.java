package Controllers;

import Models.*;
import Utils.*;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class TasksController {
    
    private static final Connection conn = MySQLConnector.getConnection();
    
    public static Task GetById(int id) {
        
        Task task = null;
        
        try {
            String query = "SELECT * FROM tasks WHERE Id = (?)";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                task = new Task(    result.getInt("Id"),
                                    result.getInt("MilestoneId"),
                                    result.getInt("AssignedToId"),
                                    result.getInt("PriorityId"),
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
        
        return task;
    }
    
    public static List<Task> GetAll() {
        
        List<Task> tasks = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM tasks";
            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while (result.next()) {
                Task task = new Task(   result.getInt("Id"),
                                        result.getInt("MilestoneId"),
                                        result.getInt("AssignedToId"),
                                        result.getInt("PriorityId"),
                                        result.getString("Title"),
                                        result.getDate("StartDate"),
                                        result.getDate("EndDate"),
                                        result.getString("Description"));
                tasks.add(task);
            }
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return tasks;
    }
    
    
    public static Task Create(Task task) {
        
        try {
            String query = "INSERT INTO tasks " +
                            "(MilestoneId, AssignedToId, PriorityId, Title, StartDate, EndDate, Description) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement statement = conn.prepareStatement(query);
            
            statement.setInt(1, task.getMilestoneId());
            statement.setInt(2, task.getAssignedToId());
            statement.setInt(3, task.getPriorityId());
            statement.setString(4, task.getTitle());
            statement.setDate(5, new java.sql.Date(task.getStartDate().getTime()));
            statement.setDate(6, new java.sql.Date(task.getEndDate().getTime()));
            statement.setString(7, task.getDescription());
            
            ResultSet result = statement.executeQuery();
            
            ResultSet idResult = statement.getGeneratedKeys();
            
            if (idResult.next()) {
                task.setId(idResult.getInt(1));
            }
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
        
        return task;
    }
    
    public static Task Update(Task task) {
        
        try {
            String query = "UPDATE tasks " + 
                           "SET MilestoneId = ?, AssignedToId = ?, PriorityId = ?, Title = ?, StartDate = ?, EndDate = ?, Description = ? " +
                           "WHERE Id = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            
            statement.setInt(1, task.getMilestoneId());
            statement.setInt(2, task.getAssignedToId());
            statement.setInt(3, task.getPriorityId());
            statement.setString(4,task.getTitle());
            statement.setDate(5, new java.sql.Date(task.getStartDate().getTime()));
            statement.setDate(6, new java.sql.Date(task.getEndDate().getTime()));    
            statement.setString(7, task.getDescription());
            statement.setInt(8,task.getId());
            
            statement.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
        
        return task;
    }
}
