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
    
    
    public boolean Log_in(String username,String password)
    {
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
    
    
    
}
