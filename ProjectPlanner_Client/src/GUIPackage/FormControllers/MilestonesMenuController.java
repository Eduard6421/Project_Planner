package GUIPackage.FormControllers;

import Controllers.MilestonesController;
import GUIPackage.AddMilestoneMenu;
import GUIPackage.AddProjectMenu;
import GUIPackage.MilestonesMenu;
import GUIPackage.ProjectsMenu;
import Models.Milestone;
import Models.Project;
import Utils.GlobalData;
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


    /**
     * Creates new form MilestonesMenuController
     *
     * @param tmp
     */
    public MilestonesMenuController(ProjectsMenuController tmp) {

        parentController = tmp;
        parentController.setWindowInvisible();

        milestonesMenu = new MilestonesMenu(this);
        milestonesMenu.setVisible(true);

        retrievePopulation();

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
                        MilestonesController.deleteById(milestoneId);
                        retrievePopulation();

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                case "Edit Milestone":
                    try {
                        addMilestoneMenu = new AddMilestoneMenu(this);
                        addMilestoneMenu.ShowSelectedMilestone();
                        addMilestoneMenu.setVisible(true);
                        addMilestoneMenu.SetActionCommand(false);
                        toggleFocus();
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
                    toggleFocus();
                    break;

                default:
                    System.out.println("Exit");
                    milestonesMenu.setVisible(false);
                    milestonesMenu.dispose();
                    parentController.setWindowVisible();
                    parentController.retrievePopulation();

            }
        } else {

            switch (command) {
                case "Exit":
                    addMilestoneMenu.setVisible(false);
                    addMilestoneMenu.dispose();
                    toggleFocus();
                    retrievePopulation();

                    break;
                case "Insert":
                    addMilestoneMenu.setVisible(false);
                    addMilestoneMenu.dispose();
                    Milestone newMilestone = addMilestoneMenu.GetMilestone();
                    if (newMilestone != null) {
                        MilestonesController.create(newMilestone);   
                    }
                    toggleFocus();
                    retrievePopulation();

                    break;

                case "Edit":
                    addMilestoneMenu.setVisible(false);
                    addMilestoneMenu.dispose();

                    Milestone EditedMilestone = addMilestoneMenu.GetMilestone();
                    EditedMilestone.setId(milestonesList.get(milestonesMenu.getLastSelectedId()).getId());
                    MilestonesController.update(EditedMilestone);

                    toggleFocus();
                    retrievePopulation();
                    break;

            }
        }
    }

    public void retrievePopulation() {
        milestonesList = milestonesMenu.ShowPopulation();
    }

    public Milestone getSelectedMilestone() {
        Milestone milestone = milestonesMenu.GetSelectedMilestone();
        return milestone;

    }

    public void toggleFocus() {
        focus = !focus;
    }

    public void setWindowInvisible() {
        milestonesMenu.setVisible(false);

    }

    public void setWindowVisible() {
        milestonesMenu.setVisible(true);
    }

    public void closeWindow() {
        milestonesMenu.setVisible(false);
        milestonesMenu.dispose();
    }
}
