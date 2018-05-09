package Controllers;

import Models.*;
import Utils.*;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class TasksController {

    private static final Connection conn = MySQLConnector.getConnection();

    /**
     * Returns object of type Task from SQL Database that has the same id as the
     * argument. Returns null on not found
     */
    public static Task GetById(int id) {

        Task task = null;

        try {
            String query = "SELECT * FROM tasks WHERE Id = (?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

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

            statement.close();
        } catch (Exception e) {
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

            statement.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return tasks;
    }
    
    public static List<Task> GetAllByMilestoneId(int milestoneId) {

        List<Task> tasks = new ArrayList<>();

        try {
            String query = "SELECT * FROM tasks WHERE milestoneId = (?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, milestoneId);
            ResultSet result = statement.executeQuery();

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

            statement.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return tasks;
    }

    public static Task Create(Task task) {

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
            return null;
        }

        return task;
    }

    public static Task Update(Task task) {

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
            return null;
        }

        return task;
    }

    public static void DeleteById(int id) {
        try {
            String query = "DELETE FROM tasks WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            statement.executeUpdate();

            statement.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public static void FinishTask(int id) {
        try {
            String query = "UPDATE tasks "
                    + "SET Finished = 1 "
                    + "WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public static void OpenTask(int id) {
        try {
            String query = "UPDATE tasks "
                    + "SET Finished = 0 "
                    + "WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
