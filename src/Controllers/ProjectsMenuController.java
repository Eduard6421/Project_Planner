/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;



import GUIPackage.ProjectMenu;
import Utils.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



public class ProjectsMenuController implements ActionListener {
    
    private ProjectMenu view ;
    private LoginMenuController parent_controller;
    private static final Connection conn = MySQLConnector.getConnection();
    

    public ProjectsMenuController(LoginMenuController controller)
    {
     
        parent_controller = controller;
        parent_controller.SetWindowInvisible();
        
        view = new ProjectMenu(this);
        view.setVisible(true);
        RetrievePopulation();
               
       
    }
    
    
    
    public  void RetrievePopulation()
    {
        view.ShowPopulation();
    }
    

    
    @Override
    public void actionPerformed(ActionEvent evt){
       

        String command = evt.getActionCommand();
        
        switch(command){
      
                case "View Milestones":
                    System.out.println("Milestones");
                    MilestonesMenuController tmp = new MilestonesMenuController(this);
                    
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
                    view.setVisible(false);
                    view.dispose();
                    parent_controller.SetWindowVisible();
        
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
