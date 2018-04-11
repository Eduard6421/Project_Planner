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
    
    private static final Connection conn = MySQLConnector.getConnection();
    
    /**
     * Creates new form MilestonesMenuController
     */
    public MilestonesMenuController() {
   
       view = new MilestoneMenu(this);
       view.setVisible(true);
        
    
    }

    @Override
    public void actionPerformed(ActionEvent evt){
       
        String command = evt.getActionCommand();
        
        switch(command){
      
                case "View Tasks":
                    System.out.println("Milestones");
                    break;
                case "Delete Milestone":
                    System.out.println("Delete Project");
                    break;
                case "Edit Milestone":
                    System.out.println("Edit Project");
                    break;
                case "New Milestone":
                    System.out.println("New Project");
                    break;
                default:
                    System.out.println("Exit");
        
            }
    }


}
    
    
    
    
    
    