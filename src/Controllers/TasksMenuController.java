/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIPackage.TaskMenu;
import Utils.MySQLConnector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 *
 * @author Eduard
 */
public class TasksMenuController implements ActionListener {
    
    
    private TaskMenu view;
    private MilestonesMenuController parent_controller;
    private static final Connection conn = MySQLConnector.getConnection();
    
    public TasksMenuController(MilestonesMenuController tmp)
    {
     parent_controller = tmp;
     parent_controller.SetWindowInvisible();
     
     view = new TaskMenu(this);
     view.setVisible(true);
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent evt){
       
        String command = evt.getActionCommand();
        
        switch(command){
      
                case "Delete Task":
                    System.out.println("Delete Task");
                    
                    break;
                case "Edit Task":
                    System.out.println("Edit Task");
                    break;
                case "New Task":
                    System.out.println("New Task");
                    break;
                default:
                    view.setVisible(false);
                    view.dispose();
                    parent_controller.SetWindowVisible();
                    System.out.println("Exit");
        
            }
    }
    

}
