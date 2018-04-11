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
    
    private static final Connection conn = MySQLConnector.getConnection();
    
    public TasksMenuController()
    {
     view = new TaskMenu(this);
     view.setVisible(true);
        
        
    }
    
       @Override
    public void actionPerformed(ActionEvent evt){
       
        String command = evt.getActionCommand();
        
        switch(command){
      
                case "Delete Task":
                    System.out.println("Delete Project");
                    break;
                case "Edit Task":
                    System.out.println("Edit Project");
                    break;
                case "New Task":
                    System.out.println("New Project");
                    break;
                default:
                    System.out.println("Exit");
        
            }
    }
    

}
