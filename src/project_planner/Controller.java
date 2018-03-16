/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_planner;

/**
 *
 * @author Eduard
 */
public class Controller {
    
    DBConnector connection;
    
    private int id_rights;
    private int project_id;
    private int milestone_id;
    private int task_id;
    private boolean connected;
    
    private String Current_Username;
     
    
    Controller(String Username,String Password)
    {
        connection = new DBConnector("client","pass4client");
        if(connection.Log_in(Username,Password))
        {
           Current_Username = Username;
           id_rights = connection.getUserRights();
           connected = true;
        }
    }
   
    public boolean isConnected()
    {
     return connected;   
    }

    
}
