package GUIPackage.FormControllers;

import GUIPackage.LoginMenu;
import Controllers.AuthController;
import Models.User;
import Utils.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;

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
         ProjectsMenuController projectsMenuController = new ProjectsMenuController(this);        
        }
        else
        {
            System.out.println("There is no such user / Database connectivity issues");
                    final JFrame popup = new JFrame();

            JButton button = new JButton();
            button.setText("Wrong credentials or database connectivity issues. Try again!");

            popup.setPreferredSize(new Dimension(500, 200));
            popup.add(button);
            popup.pack();
            popup.setVisible(true);

            button.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    popup.setVisible(false);
                    popup.dispose();
                }
            });
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
