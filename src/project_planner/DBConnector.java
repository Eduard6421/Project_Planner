/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_planner;

import Data_Structures.milestone;
import Data_Structures.project;
import Data_Structures.task;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Eduard
 */
public class DBConnector {
    
    private Connection connection;
    private int user_rights;  //0 = administrator 1 = manager 2 = dunno sth random
   
    public DBConnector(String username,String password){
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_database",username,password);
     }
     catch( Exception e)
     {
         System.out.println("Error : "+ e);
     }   
    }
      
    public boolean Log_in(String username,String password){
        boolean logged_in = false;
        
        try
        {
            PreparedStatement Stat = connection.prepareStatement("SELECT COUNT(1),Rights FROM users WHERE Username = (?) and Password = (?)");
            Stat.setString(1,username);
            Stat.setString(2, password);
            ResultSet result = Stat.executeQuery();
            while(result.next())
            {
                logged_in = result.getBoolean("COUNT(1)");
                user_rights = result.getInt("Rights");
            }          
        }
        catch(Exception e)
        {
            System.out.println("Error : " + e);   
        }
        
        return logged_in;
    
    }
    
    public boolean Create_Project(String project_name,String client_name,java.util.Date start_date,java.util.Date end_date,double project_budget){
  
          try
          {
              PreparedStatement Stat = connection.prepareStatement("INSERT INTO `project_database`.`projects` (`Project_Name`, `Client_Name`, `Start_Date`, `End_Date`, `Budget`) VALUES ( ?, ?, ?, ?, ?);");
              
              java.sql.Date sql_start_date = new java.sql.Date(start_date.getTime());
              java.sql.Date sql_end_date   = new java.sql.Date(end_date.getTime());
              
              Stat.setString(1,project_name);
              Stat.setString(2,client_name);
              Stat.setDate(3,sql_start_date);
              Stat.setDate(4,sql_end_date);
              Stat.setDouble(5,project_budget);
              
              Stat.executeUpdate();
              
              return true;
      
          }
          catch(Exception e)
          {
              System.out.println("Project Create Error : "+ e);
              return false;
              
          }
        
    }
    
    public boolean Create_Milestone(int project_id,String milestone_title,java.util.Date start_date,java.util.Date end_date,String description){
  
          try
          {
              
              PreparedStatement Stat = connection.prepareStatement("INSERT INTO `project_database`.`milestones` (`Title`, `Start_Date`, `End_date`, `Description`, `Assigned_Project`) VALUES ((?),(?), (?), (?), (?));");
              
              java.sql.Date sql_start_date = new java.sql.Date(start_date.getTime());
              java.sql.Date sql_end_date   = new java.sql.Date(end_date.getTime());
              
              Stat.setString(1,milestone_title);
              Stat.setDate(2,sql_start_date);
              Stat.setDate(3,sql_end_date);
              Stat.setString(4,description);
              Stat.setInt(5,project_id); 
              
              Stat.executeUpdate();
              
              return true;
      
          }
          catch(Exception e)
          { 
              System.out.println("Milestone Create Error : "+ e);
              return false;
              
          }
        
    }  
    
    public boolean Create_Task(int milestone_id,int developer_id,String task_title,int priority,String description,java.util.Date end_date){
          try
          {
              
              PreparedStatement Stat = connection.prepareStatement("INSERT INTO `project_database`.`tasks` (`Title`, `Priority`, `Description`,`End_Date`,`Developer`,`Assigned_Milestone`) VALUES ( (?), (?),(?),(?),(?),(?));");
             
              java.sql.Date sql_end_date   = new java.sql.Date(end_date.getTime());
              
              Stat.setString(1,task_title);
              Stat.setInt(2,priority);
              Stat.setString(3,description);
              Stat.setDate(4,sql_end_date);
              Stat.setInt(5,milestone_id); 
              Stat.setInt(6,developer_id);
              
              Stat.executeUpdate();
              
              return true;
      
          }
          catch(Exception e)
          { 
              System.out.println("Milestone Create Error : "+ e);
              return false;
              
          }
        
    }  

    
    
    public boolean Update_Project(String Project_Name,String Client_Name, String NewProject_Name,String NewClient_Name,Date NewStart_Date,Date NewEnd_Date,int NewBudget)
    {    
          try
          {
              PreparedStatement Stat = connection.prepareStatement("UPDATE projects \n" +
"SET Project_Name = (?), Client_Name=(?),Start_Date=(?),End_Date=(?),Budget=(?) \n" +
"WHERE (Project_Name=(?) AND Client_Name=(?));");
              
              java.sql.Date sql_start_date = new java.sql.Date(NewStart_Date.getTime());
              java.sql.Date sql_end_date   = new java.sql.Date(NewEnd_Date.getTime());
              
              Stat.setString(1,NewProject_Name);
              Stat.setString(2,NewClient_Name);
              Stat.setDate(3,sql_start_date);
              Stat.setDate(4,sql_end_date);
              Stat.setDouble(5,NewBudget);
              Stat.setString(6, Project_Name);
              Stat.setString(7, Client_Name);

              Stat.executeUpdate();
              
              return true;
      
          }
          catch(Exception e)
          {
              System.out.println("Project Create Error : "+ e);
              return false;
              
          }
    }
    
    
    
    public boolean Delete_Project(String Project_Name, String Client_Name)
    {
     try
          {
              PreparedStatement Stat = connection.prepareStatement("DELETE FROM projects\n" + "WHERE Project_Name=(?) AND Client_Name =(?);");
              
              Stat.setString(1,Project_Name);
              Stat.setString(2,Client_Name);              
              Stat.executeUpdate();
              return true;
      
          }
          catch(Exception e)
          {
              System.out.println("Project Create Error : "+ e);
              return false;
              
          }
    }
    public boolean Delete_Milestone(String Milestone_Title, int Assigned_Project)
    {
        try
          {
              PreparedStatement Stat = connection.prepareStatement("DELETE FROM milestones\n" + "WHERE Title = (?) AND Assigned_Project = (?);");
              
              Stat.setString(1,Milestone_Title);
              Stat.setInt(2,Assigned_Project);              
              Stat.executeUpdate();
              
              return true;
      
          }
          catch(Exception e)
          {
              System.out.println("Project Create Error : "+ e);
              return false;
              
          }
        
    }
    public boolean Delete_Task(String task_title, int Assigned_Milestone)
    {
        try
          {
              PreparedStatement Stat = connection.prepareStatement("DELETE FROM tasks\n" + "WHERE title =(?) AND Assigned_Milestone = (?);");
              
              Stat.setString(1,task_title);
              Stat.setInt(2,Assigned_Milestone);              
              Stat.executeUpdate();
              
              return true;
      
          }
          catch(Exception e)
          {
              System.out.println("Project Create Error : "+ e);
              return false;
              
          }
        
    }
    
    
    
    public List<project> Get_Projects(){
        
        List<project> answer = new ArrayList<>();
        
        try
        {
            String query = "SELECT idProjectTable `ID`, Project_Name , Client_Name, Start_Date,End_Date,Budget from projects";
            
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(query);
            
            
            while(result.next())
            {
                project tmp = new project(result.getInt("ID"),result.getString("Project_Name"),result.getString("Client_Name"),result.getDate("Start_date"),result.getDate("End_Date"),result.getDouble("Budget"));
                answer.add(tmp);
                
            }          
            
            stm.close();
            
        }
        catch(Exception e)
        {
            System.out.println("Error " + e );
        }
        return answer;
        
    }
    
    public List<milestone> Get_Milestones(int ProjectID){
        
         List<milestone> answer = new ArrayList<>();
        
            try
            {
            PreparedStatement Stat = connection.prepareStatement("SELECT Idmilestones `ID`, Title, Start_date,End_Date,Description FROM milestones\n" +
"WHERE `Assigned_Project` = (?);");
              
            Stat.setInt(1,ProjectID);
              
            ResultSet result = Stat.executeQuery();
              
            while(result.next())
            {
                milestone tmp = new milestone(result.getInt("ID"),result.getString("Title"),result.getDate("Start_date"),result.getDate("End_date"),result.getString("Description"));
                answer.add(tmp);
            }
              
        }
        catch(Exception e)
        {
            System.out.println("Error "+ e);   
        }
            
            
        return answer;
    
    }
    
    public List<task> Get_Tasks(int MilestoneID)    
    {
        List<task> answer = new ArrayList<>();
        
            try
            {
            PreparedStatement Stat = connection.prepareStatement("SELECT idtasks `ID`,Title,Priority,Description,End_Date,Developer\n" +
"FROM tasks\n" +
"WHERE Assigned_Milestone=(?);");
              
            Stat.setInt(1,MilestoneID);
              
            ResultSet result = Stat.executeQuery();
              
            while(result.next())
            {
                task tmp = new task(result.getInt("ID"),result.getString("Title"),result.getString("Priority"),result.getString("Description"),result.getDate("End_date"),result.getInt("Developer"));
                answer.add(tmp);
            }
              
        }
        catch(Exception e)
        {
            System.out.println("Error "+ e);   
        }
            
            
        return answer;
        
        
    }
       
    
    
    int getUserRights() {
        return user_rights;
    }
      
}