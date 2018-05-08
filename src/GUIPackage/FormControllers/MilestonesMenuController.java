package GUIPackage.FormControllers;

import Controllers.MilestonesController;
import GUIPackage.AddMilestoneMenu;
import GUIPackage.AddProjectMenu;
import GUIPackage.MilestonesMenu;
import GUIPackage.ProjectsMenu;
import Models.Milestone;
import Models.Project;
import Utils.GlobalData;
import Utils.MySQLConnector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class MilestonesMenuController implements ActionListener {
    
    private MilestonesMenu milestonesMenu;
    private AddMilestoneMenu addMilestoneMenu;
    private ProjectsMenuController parentController;
    private List<Milestone> milestonesList;

    private boolean focus = true;

    private static final Connection conn = MySQLConnector.getConnection();

    /**
     * Creates new form MilestonesMenuController
     *
     * @param tmp
     */
    public MilestonesMenuController(ProjectsMenuController tmp) {

        parentController = tmp;
        parentController.SetWindowInvisible();

        milestonesMenu = new MilestonesMenu(this);
        milestonesMenu.setVisible(true);

        RetrievePopulation();

    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        String command = evt.getActionCommand();
        if (focus) {
            switch (command) {

                case "View Tasks":
                    try {
                        int milestoneId = milestonesList.get(milestonesMenu.getLastSelectedId()).getId();
                        if (milestoneId < 0) {
                            throw new Exception("No Milestone Selected");
                        }
                        GlobalData.setMilestoneId(milestoneId);
                        Milestone selectedMilestone = milestonesMenu.GetSelectedMilestone();
                        GlobalData.setMilestoneTitle(selectedMilestone.getTitle());
                        
                        TasksMenuController tasksMenuController = new TasksMenuController(this);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case "Delete Milestone":
                    try {
                        milestonesMenu.GetSelectedMilestone();
                        int milestoneId = milestonesList.get(milestonesMenu.getLastSelectedId()).getId();
                        if (milestoneId < 0) {
                            throw new Exception("No Milestone Selected");
                        }
                        MilestonesController.DeleteById(milestoneId);
                        RetrievePopulation();

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                case "Edit Milestone":
                    try {
                        addMilestoneMenu = new AddMilestoneMenu(this);
                        addMilestoneMenu.ShowSelectedMilestone();
                        addMilestoneMenu.setVisible(true);
                        addMilestoneMenu.SetActionCommand(false);
                        ToggleFocus();
                    } catch (Exception e) {
                        System.out.println(e);
                        addMilestoneMenu.setVisible(false);
                        addMilestoneMenu.dispose();
                    }
                    break;
                case "New Milestone":
                    addMilestoneMenu = new AddMilestoneMenu(this);
                    addMilestoneMenu.setVisible(true);
                    addMilestoneMenu.SetActionCommand(true);
                    ToggleFocus();
                    break;

                default:
                    System.out.println("Exit");
                    milestonesMenu.setVisible(false);
                    milestonesMenu.dispose();
                    parentController.SetWindowVisible();
                    parentController.RetrievePopulation();

            }
        } else {

            switch (command) {
                case "Exit":
                    addMilestoneMenu.setVisible(false);
                    addMilestoneMenu.dispose();
                    ToggleFocus();
                    RetrievePopulation();

                    break;
                case "Insert":
                    addMilestoneMenu.setVisible(false);
                    addMilestoneMenu.dispose();
                    Milestone newMilestone = addMilestoneMenu.GetMilestone();
                    if (newMilestone != null) {
                        MilestonesController.Create(newMilestone);   
                    }
                    ToggleFocus();
                    RetrievePopulation();

                    break;

                case "Edit":
                    addMilestoneMenu.setVisible(false);
                    addMilestoneMenu.dispose();

                    Milestone EditedMilestone = addMilestoneMenu.GetMilestone();
                    EditedMilestone.setId(milestonesList.get(milestonesMenu.getLastSelectedId()).getId());
                    MilestonesController.Update(EditedMilestone);

                    ToggleFocus();
                    RetrievePopulation();
                    break;

            }
        }
    }

    public void RetrievePopulation() {
        milestonesList = milestonesMenu.ShowPopulation();
    }

    public Milestone GetSelectedMilestone() {
        Milestone milestone = milestonesMenu.GetSelectedMilestone();
        return milestone;

    }

    public void ToggleFocus() {
        focus = !focus;
    }

    public void SetWindowInvisible() {
        milestonesMenu.setVisible(false);

    }

    public void SetWindowVisible() {
        milestonesMenu.setVisible(true);
    }

    public void CloseWindow() {
        milestonesMenu.setVisible(false);
        milestonesMenu.dispose();
    }
}
