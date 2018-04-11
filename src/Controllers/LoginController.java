/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import GUIPackage.LoginMenu;
import Models.User;
import Utils.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginController implements ActionListener{

    private LoginMenu view;
    
    private static final Connection conn = MySQLConnector.getConnection();

    public LoginController()
    {
        
       view = new LoginMenu();
       view.AddEventListener(this);
       view.setVisible(true);
        
        
    }
    
    public void  SetWindowInvisible()
    {
        view.setVisible(false);
        
    }
    public void SetWindowVisible()
    {
     view.setVisible(true);   
    }
    
    public void CloseWindow()
    {
        view.setVisible(false);
        view.dispose();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent evt){
       
        String username = view.GetUsername();
        String password = view.GetPassword();
        
        if(LogIn(username,password))
        {
         view.setVisible(false);   
            
        }
        else
        {
            System.out.println("THere is no such user / Database connectivity issues");
        }
        
        
        
    }
    
    public boolean LogIn(String username, String password) {
        try {
            String query = "SELECT COUNT(1), u.Username, u.Id, r.Title " +
                    "FROM users u INNER JOIN roles r ON r.Id = u.RoleId " +
                    "WHERE Username = (?) and Password = (?)";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                GlobalData.setLoggedIn(result.getBoolean("COUNT(1)"));
                
                if (!GlobalData.isLoggedIn()) {
                    System.out.println("No such user exists!");
                    return false;
                } 
                
                GlobalData.setUsername(result.getString("u.Username"));
                GlobalData.setUserId(result.getInt("u.Id"));
                GlobalData.setRoleTitle(result.getString("r.Title"));
            }
            
            statement.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
        
        return true;
    }
    
   
    public void LogOut(){
        GlobalData.setLoggedIn(false);
        GlobalData.setRoleTitle(null);
        GlobalData.setUserId(0);
        GlobalData.setUsername(null);
    }
}
