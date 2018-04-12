package Controllers;

import Models.*;
import Utils.*;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class ProjectsController {
    
    private static final Connection conn = MySQLConnector.getConnection();
    
    public static Project GetById(int id) {
        
        Project project = null;
        
        try {
            String query = "SELECT * FROM projects WHERE Id = (?)";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            

            while (result.next()) {
                project = new Project(result.getInt("Id"),
                                    result.getInt("ManagerId"),
                                    result.getString("Title"),
                                    result.getString("ClientName"),
                                    result.getDate("StartDate"),
                                    result.getDate("EndDate"),
                                    result.getDouble("Budget"),
                                    result.getString("Description"));
            }
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return project;
    }
    
    public static List<Project> GetAll() {
        
        List<Project> projects = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM projects";
            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            
            while (result.next()) {
                Project project = new Project(result.getInt("Id"),
                                            result.getInt("ManagerId"),
                                            result.getString("Title"),
                                            result.getString("ClientName"),
                                            result.getDate("StartDate"),
                                            result.getDate("EndDate"),
                                            result.getDouble("Budget"),
                                            result.getString("Description"));
                projects.add(project);
            }
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return projects;
    }
    
    public static Project Create(Project project) {
        
        try {
            String query = "INSERT INTO projects " +
                            "(ManagerId, Title, ClientName, StartDate, EndDate, Budget, Description) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, project.getManagerId());
            statement.setString(2, project.getTitle());
            statement.setString(3, project.getClientName());
            statement.setDate(4, new java.sql.Date(project.getStartDate().getTime()));
            statement.setDate(5, new java.sql.Date(project.getEndDate().getTime()));
            statement.setDouble(6, project.getBudget());
            statement.setString(7, project.getDescription());
            
            statement.executeUpdate();
            
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
        
        return project;
    }
    
    public static Project Update(Project project) {
        
        try {
            String query = "UPDATE projects " + 
                        "SET ManagerId = ?, Title = ?, ClientName = ?, StartDate = ?, EndDate = ?, Budget = ?, Description = ? " +
                        "WHERE Id = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, project.getManagerId());
            statement.setString(2, project.getTitle());
            statement.setString(3, project.getClientName());
            statement.setDate(4, new java.sql.Date(project.getStartDate().getTime()));
            statement.setDate(5, new java.sql.Date(project.getEndDate().getTime()));
            statement.setDouble(6, project.getBudget());
            statement.setString(7, project.getDescription());
            statement.setInt(8, project.getId());
            
            statement.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
        
        return project;
    }
    
    
        public static void DeleteById(int id)
    {
        
        
        try {
            String query = "DELETE FROM projects WHERE Id=? ";
            
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
