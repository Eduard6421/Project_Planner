package Controllers;

import Models.*;
import Server.Server;
import Utils.*;
import com.sun.rowset.CachedRowSetImpl;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javafx.util.Pair;
import javax.sql.rowset.CachedRowSet;

public class ProjectsController {
    
    private static final Connection conn = MySQLConnector.getConnection();
    
    public static void callGetById() throws IOException {
        int id = Server.receiveInt();
        
        getById(id);
    }
    
    public static void callGetAll() {
        getAll();
    }
    
    public static void callGetAllBetweenDates() throws IOException, ParseException {
        Date startDate = Server.receiveDate();
        Date endDate = Server.receiveDate();
        
        getAllBetweenDates(startDate, endDate);
    }
    
    public static void callCreate() throws IOException, ClassNotFoundException {
        Project project = (Project) Server.receiveObject();
        
        create(project);
    }
    
    public static void callUpdate() throws IOException, ClassNotFoundException {
        Project project = (Project) Server.receiveObject();
        
        update(project);
    }
    
    public static void callDeleteById() throws IOException {
        int id = Server.receiveInt();
        
        deleteById(id);
    }
    
    public static void callGetProjectStatusById() throws IOException {
        int id = Server.receiveInt();
        
        getProjectStatusById(id);
    }
    
    public static Project getById(int id) {
        
        Project project = null;
        
        try {
            String query = "SELECT * FROM projects WHERE Id = (?)";
            
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
        
        return project;
    }
    
    public static List<Project> getAll() {
        
        List<Project> projects = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM projects";
            
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
        
        return projects;
    }
    
   public static List<Project> getAllBetweenDates(Date startDate, Date endDate) {
        
        List<Project> projects = new ArrayList<>();
        
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String startDateString = "'" + dateFormatter.format(startDate) + "'";
            String endDateString = "'" + dateFormatter.format(endDate) + "'";
            
            String query = "SELECT * FROM projects p " + 
                           "WHERE p.StartDate >= ? AND p.EndDate <= ? ";
            
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));
            
            ResultSet resultSet = statement.executeQuery(query);
            CachedRowSet result = new CachedRowSetImpl();
            result.populate(resultSet);
            
            Server.sendObject(result);
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return projects;
    }
    
    public static Boolean create(Project project) throws IOException {
        
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
            Server.sendBoolean(false);
            return false;
        }
        
        Server.sendBoolean(true);
        return true;
    }
    
    public static Project update(Project project) throws IOException {
        
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
            Server.sendBoolean(false);
            return null;
        }
        
        Server.sendBoolean(true);
        return project;
    }
    
    
    public static Boolean deleteById(int id) throws IOException
    {
        
        
        try {
            String query = "DELETE FROM projects WHERE Id = ?";
            
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
        return true;
    }
 
    //Number of finished tasks/Number of tasks
    public static Pair<Integer, Integer> getProjectStatusById(int id) throws IOException {
        
        Pair<Integer, Integer> resultPair = new Pair<Integer, Integer>(-1, -1);
        
        try {
            String query = "SELECT COUNT(t.Finished), " +
                    "SUM(CASE WHEN t.Finished = 1 THEN 1 ELSE 0 END) " +
                    "FROM projects p JOIN milestones m ON m.ProjectId = p.Id JOIN tasks t ON t.MilestoneId = m.Id " +
                    "WHERE p.Id = ?";
            
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
