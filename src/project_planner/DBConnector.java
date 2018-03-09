/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_planner;


import java.sql.*;




/**
 *
 * @author Eduard
 */
public class DBConnector {
    
    private Connection connection;
    private int user_rights;  //0 = administrator 1 = manager 2 = dunno sth random
   
    
    public DBConnector(){
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_database","client","pass4client");
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
              PreparedStatement Stat = connection.prepareStatement("INSERT INTO `project_database`.`projecttable` (`Project_Name`, `Client_Name`, `Start_Date`, `End_Date`, `Budget`) VALUES ( ?, ?, ?, ?, ?);");
              
              Stat.setString(1,project_name);
              Stat.setString(2,client_name);
              Stat.setDate(3, (Date) start_date);
              Stat.setDate(4, (Date)   end_date);
              Stat.setDouble(5,project_budget);
              
              Stat.executeUpdate();
              
              
              return true;
      
          }
          catch(Exception e)
          {
              System.out.println("Eror : "+ e);
              return false;
              
          }
        
    }
    
}
    