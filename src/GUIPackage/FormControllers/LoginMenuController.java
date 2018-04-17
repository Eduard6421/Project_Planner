package GUIPackage.FormControllers;

import GUIPackage.LoginMenu;
import Controllers.AuthController;
import Models.User;
import Utils.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginMenuController implements ActionListener {
    
    private LoginMenu view;
    
    private static final Connection conn = MySQLConnector.getConnection();

    public LoginMenuController()
    {
       view = new LoginMenu(this);
       view.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {     
        String username = view.GetUsername();
        String password = view.GetPassword();
        
        if(AuthController.LogIn(username,password))
        {
         ProjectsMenuController tmp = new ProjectsMenuController(this);        
        }
        else
        {
            System.out.println("There is no such user / Database connectivity issues");
        }     
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
}
