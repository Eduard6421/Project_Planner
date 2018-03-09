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
    private Statement statement;
    private ResultSet result;
    
    
    public DBConnector()
    {
     try
     {
         Class.forName("com.mysql.jdbc.Driver"); 
         connection = DriverManager.getConnection("jdbc::mysql://localhost:3306/project_database");
         statement  = connection.createStatement();
     }
     catch( Exception e)
     {
         System.out.println("Error : "+ e);
     }
     
        
        
    }
    
    
    
}
