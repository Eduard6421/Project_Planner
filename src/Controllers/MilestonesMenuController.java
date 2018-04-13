package Controllers;

import GUIPackage.AddMilestoneMenu;
import GUIPackage.AddProjectMenu;
import GUIPackage.MilestoneMenu;
import GUIPackage.ProjectMenu;
import Models.Milestone;
import Models.Project;
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
    private AddMilestoneMenu add_view;
    private ProjectsMenuController parent_controller;
    private static final Connection conn = MySQLConnector.getConnection();
     private boolean focus = true;
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
        if(focus){
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
                    add_view = new AddMilestoneMenu(this);
                    add_view.setVisible(true);
                    add_view.SetActionCommand(true);
                    ToggleFocus();
                    break;
                   
                   
                default:
                    System.out.println("Exit");
                    view.setVisible(false);
                    view.dispose();
                    parent_controller.SetWindowVisible();
                    parent_controller.RetrievePopulation();
        
            }
        }else{
        
            switch (command) {
                case "Exit":
                    add_view.setVisible(false);
                    add_view.dispose();
                    ToggleFocus();
                    RetrievePopulation();
                 
                    break;
                case "Insert":
                    add_view.setVisible(false);
                    add_view.dispose();
                    Milestone new_milestone = add_view.GetMilestone();
                    MilestonesController.Create(new_milestone);
                    ToggleFocus();
                    RetrievePopulation();

                    break;

                case "Edit":
                    add_view.setVisible(false);
                    add_view.dispose();
                    ToggleFocus();
                    RetrievePopulation();
                    break;

            }
        }
    }
    
    public void ToggleFocus() {
        focus = !focus;
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
    