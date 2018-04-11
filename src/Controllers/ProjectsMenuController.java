/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;



import GUIPackage.LoginMenu;
import GUIPackage.ProjectMenu;
import Models.User;
import Utils.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



public class ProjectsMenuController implements ActionListener {
    
    private ProjectMenu view ;
    
    private static final Connection conn = MySQLConnector.getConnection();
    

    public ProjectsMenuController()
    {
     
        
        view = new ProjectMenu(this);
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
       

        String command = evt.getActionCommand();
        
        switch(command){
      
                case "View Milestones":
                    System.out.println("Milestones");
                    break;
                case "Delete Project":
                    System.out.println("Delete Project");
                    break;
                case "Edit Project":
                    System.out.println("Edit Project");
                    break;
                case "New Project":
                    System.out.println("New Project");
                    break;
                default:
                    System.out.println("Exit");
        
            }
    }
    

    
    
    
}
