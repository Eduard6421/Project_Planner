package Controllers;

import GUIPackage.MilestoneMenu;
import GUIPackage.ProjectMenu;
import Utils.MySQLConnector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 *
 * @author Eduard
 */
public class MilestonesMenuController  implements ActionListener {

    
    private MilestoneMenu view;
    private ProjectsMenuController parent_controller;
    private static final Connection conn = MySQLConnector.getConnection();
    
    /**
     * Creates new form MilestonesMenuController
     * @param tmp
     */
    public MilestonesMenuController(ProjectsMenuController tmp) {
   
       parent_controller = tmp;
       parent_controller.SetWindowInvisible();
       
       view = new MilestoneMenu(this);
       view.setVisible(true);
       
       RetrievePopulation();
       
        
    
    }
    
  
    @Override
    public void actionPerformed(ActionEvent evt){
       
        String command = evt.getActionCommand();
        
        switch(command){
      
                case "View Tasks":
                    System.out.println("View Tasks");
                    TasksMenuController tmp = new TasksMenuController(this);
                    break;
                case "Delete Milestone":
                    System.out.println("Delete Milestone");
                    break;
                case "Edit Milestone":
                    System.out.println("Edit Milestone");
                    break;
                case "New Milestone":
                    System.out.println("New Milestone");
                    break;
                default:
                    System.out.println("Exit");
                    view.setVisible(false);
                    view.dispose();
                    parent_controller.SetWindowVisible();
                    parent_controller.RetrievePopulation();
        
            }
    }
    
    public void RetrievePopulation()
    {
     view.ShowPopulation();
    }

    public void SetWindowInvisible()
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
    