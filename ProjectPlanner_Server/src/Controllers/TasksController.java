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
import javax.sql.rowset.CachedRowSet;

public class TasksController {

    private static final Connection conn = MySQLConnector.getConnection();

    public static void callGetById() throws IOException {
        int id = Server.receiveInt();
        
        getById(id);
    }
    
    public static void callGetAll() {
        getAll();
    }
    
    public static void callGetAllByMilestoneId() throws IOException, ParseException {
        int milestoneId = Server.receiveInt();
        
        getAllByMilestoneId(milestoneId);
    }
    
    public static void callCreate() throws IOException, ClassNotFoundException {
        Task task = (Task) Server.receiveObject();
        
        create(task);
    }
    
    public static void callUpdate() throws IOException, ClassNotFoundException {
        Task task = (Task) Server.receiveObject();
        
        update(task);
    }
    
    public static void callDeleteById() throws IOException {
        int id = Server.receiveInt();
        
        deleteById(id);
    }
    
    public static void callFinishTaskById() throws IOException {
        int id = Server.receiveInt();
        
        finishTaskById(id);
    }
    
    public static void callOpenTaskById() throws IOException {
        int id = Server.receiveInt();
        
        openTaskById(id);
    }
    
    public static Task getById(int id) {

        Task task = null;

        try {
            String query = "SELECT * FROM tasks WHERE Id = (?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            CachedRowSet result = new CachedRowSetImpl();
            result.populate(resultSet);  
            
            Server.sendObject(result);

            statement.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return task;
    }

    public static List<Task> getAll() {

        List<Task> tasks = new ArrayList<>();

        try {
            String query = "SELECT * FROM tasks";

            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            CachedRowSet result = new CachedRowSetImpl();
            result.populate(resultSet);
            
            Server.sendObject(result);

            statement.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return tasks;
    }
    
    public static List<Task> getAllByMilestoneId(int milestoneId) {

        List<Task> tasks = new ArrayList<>();

        try {
            String query = "SELECT * FROM tasks WHERE milestoneId = (?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, milestoneId);

            ResultSet resultSet = statement.executeQuery();
            CachedRowSet result = new CachedRowSetImpl();
            result.populate(resultSet);  
            
            Server.sendObject(result);

            statement.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return tasks;
    }

    public static Boolean create(Task task) throws IOException {

        try {
            String query = "INSERT INTO tasks "
                    + "(MilestoneId, AssignedToId, PriorityId, Title, StartDate, EndDate, Description) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setInt(1, task.getMilestoneId());
            statement.setInt(2, task.getAssignedToId());
            statement.setInt(3, task.getPriorityId());
            statement.setString(4, task.getTitle());
            statement.setDate(5, new java.sql.Date(task.getStartDate().getTime()));
            statement.setDate(6, new java.sql.Date(task.getEndDate().getTime()));
            statement.setString(7, task.getDescription());

            statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            Server.sendBoolean(false);
            return false;
        }

        Server.sendBoolean(true);
        return false;
    }

    public static Boolean update(Task task) throws IOException {

        try {
            String query = "UPDATE tasks "
                    + "SET MilestoneId = ?, AssignedToId = ?, PriorityId = ?, Title = ?, StartDate = ?, EndDate = ?, Description = ?, Finished = ? "
                    + "WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setInt(1, task.getMilestoneId());
            statement.setInt(2, task.getAssignedToId());
            statement.setInt(3, task.getPriorityId());
            statement.setString(4, task.getTitle());
            statement.setDate(5, new java.sql.Date(task.getStartDate().getTime()));
            statement.setDate(6, new java.sql.Date(task.getEndDate().getTime()));
            statement.setString(7, task.getDescription());
            statement.setBoolean(8, task.getFinished());
            statement.setInt(9, task.getId());

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error: " + e);
            Server.sendBoolean(false);
            return false;
        }

        Server.sendBoolean(true);
        return false;
    }

    public static Boolean deleteById(int id) throws IOException {
        try {
            String query = "DELETE FROM tasks WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            Server.sendBoolean(false);
            return false;
        }
        
        Server.sendBoolean(true);
        return false;
    }
    
    public static Boolean finishTaskById(int id) throws IOException {
        try {
            String query = "UPDATE tasks "
                    + "SET Finished = 1 "
                    + "WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error: " + e);
            Server.sendBoolean(false);
            return false;
        }
        
        Server.sendBoolean(true);
        return false;
    }
    
    public static Boolean openTaskById(int id) throws IOException {
        try {
            String query = "UPDATE tasks "
                    + "SET Finished = 0 "
                    + "WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error: " + e);
            Server.sendBoolean(false);
            return false;
        }
        
        Server.sendBoolean(true);
        return false;
    }
}
