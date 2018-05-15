package Controllers;

import Client.Client;
import Models.*;
import Utils.*;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;

public class TasksController {

    public static Task getById(int id) {

        Task task = null;

        try {
            Client.sendString("Tasks");
            Client.sendString("getById");
            Client.sendInt(id);
            
            CachedRowSet result = (CachedRowSet) Client.receiveObject();

            while (result.next()) {
                task = new Task(result.getInt("Id"),
                        result.getInt("MilestoneId"),
                        result.getInt("AssignedToId"),
                        result.getInt("PriorityId"),
                        result.getString("Title"),
                        result.getDate("StartDate"),
                        result.getDate("EndDate"),
                        result.getString("Description"),
                        result.getBoolean("Finished"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return task;
    }

    public static List<Task> getAll() {

        List<Task> tasks = new ArrayList<>();

        try {
            Client.sendString("Tasks");
            Client.sendString("getAll");
            
            CachedRowSet result = (CachedRowSet) Client.receiveObject();

            while (result.next()) {
                Task task = new Task(result.getInt("Id"),
                        result.getInt("MilestoneId"),
                        result.getInt("AssignedToId"),
                        result.getInt("PriorityId"),
                        result.getString("Title"),
                        result.getDate("StartDate"),
                        result.getDate("EndDate"),
                        result.getString("Description"),
                        result.getBoolean("Finished"));
                tasks.add(task);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return tasks;
    }
    
    public static List<Task> getAllByMilestoneId(int milestoneId) {

        List<Task> tasks = new ArrayList<>();

        try {
            Client.sendString("Tasks");
            Client.sendString("getAllByMilestoneId");
            Client.sendInt(milestoneId);
            
            CachedRowSet result = (CachedRowSet) Client.receiveObject();

            while (result.next()) {
                Task task = new Task(result.getInt("Id"),
                        result.getInt("MilestoneId"),
                        result.getInt("AssignedToId"),
                        result.getInt("PriorityId"),
                        result.getString("Title"),
                        result.getDate("StartDate"),
                        result.getDate("EndDate"),
                        result.getString("Description"),
                        result.getBoolean("Finished"));
                tasks.add(task);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return tasks;
    }

    public static Task create(Task task) {

        try {
            Client.sendString("Tasks");
            Client.sendString("create");
            Client.sendObject(task);
            
            Boolean created = Client.receiveBoolean();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }

        return task;
    }

    public static Task update(Task task) {

        try {
            Client.sendString("Tasks");
            Client.sendString("update");
            Client.sendObject(task);
            
            Boolean updated = Client.receiveBoolean();

        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }

        return task;
    }

    public static void deleteById(int id) {
        try {
            Client.sendString("Tasks");
            Client.sendString("deleteById");
            Client.sendInt(id);
            
            Boolean deleted = Client.receiveBoolean();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public static void finishTaskById(int id) {
        try {
            Client.sendString("Tasks");
            Client.sendString("finishTaskById");
            Client.sendInt(id);
            
            Boolean finished = Client.receiveBoolean();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public static void openTaskById(int id) {
        try {
            Client.sendString("Tasks");
            Client.sendString("openTaskById");
            Client.sendInt(id);
            
            Boolean opened = Client.receiveBoolean();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
