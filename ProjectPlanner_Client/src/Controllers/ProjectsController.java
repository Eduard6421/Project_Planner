package Controllers;

import Client.Client;
import Models.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javafx.util.Pair;
import javax.sql.rowset.CachedRowSet;

public class ProjectsController {
    
    public static Project GetById(int id) {
        
        Project project = null;
        
        try {
            Client.sendString("Projects");
            Client.sendString("getById");
            Client.sendInt(id);
            
            CachedRowSet result = (CachedRowSet) Client.receiveObject();

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
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return project;
    }
    
    public static List<Project> GetAll() {
        
        List<Project> projects = new ArrayList<>();
        
        try {       
            Client.sendString("Projects");
            Client.sendString("getAll");
            
            CachedRowSet result = (CachedRowSet) Client.receiveObject();
            
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
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return projects;
    }
    
    public static List<Project> GetAllBetweenDates(Date startDate, Date endDate) {
        
        List<Project> projects = new ArrayList<>();
        
        try {
            Client.sendString("Projects");
            Client.sendString("getAllBetweenDates");
            Client.sendDate(startDate);
            Client.sendDate(endDate);
            
            CachedRowSet result = (CachedRowSet) Client.receiveObject();
            
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
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return projects;
    }
    
    public static Project Create(Project project) {
        
        try {
            Client.sendString("Projects");
            Client.sendString("create");
            Client.sendObject(project);
            
            Boolean created = Client.receiveBoolean();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
        
        return project;
    }
    
    public static Project Update(Project project) {
        
        try {
            Client.sendString("Projects");
            Client.sendString("update");
            Client.sendObject(project);
            
            Boolean updated = Client.receiveBoolean();
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
            Client.sendString("Projects");
            Client.sendString("deleteById");
            Client.sendInt(id);

            Boolean deleted = Client.receiveBoolean();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
 
    //Number of finished tasks/Number of tasks
    public static Pair<Integer, Integer> GetProjectStatusById(int id) {
        
        Pair<Integer, Integer> resultPair = null;
        
        try {
            Client.sendString("Projects");
            Client.sendString("getProjectStatusById");
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
