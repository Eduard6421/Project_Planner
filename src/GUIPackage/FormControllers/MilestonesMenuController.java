package GUIPackage.FormControllers;

import Controllers.MilestonesController;
import GUIPackage.AddMilestoneMenu;
import GUIPackage.AddProjectMenu;
import GUIPackage.MilestonesMenu;
import GUIPackage.ProjectsMenu;
import Models.Milestone;
import Models.Project;
import Utils.MySQLConnector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class MilestonesMenuController implements ActionListener {
    
        private MilestonesMenu view;
    private AddMilestoneMenu add_view;
    private ProjectsMenuController parent_controller;
    private List<Milestone> milestone_list;

    private boolean focus = true;

    private static final Connection conn = MySQLConnector.getConnection();

    /**
     * Creates new form MilestonesMenuController
     *
     * @param tmp
     */
    public MilestonesMenuController(ProjectsMenuController tmp) {

        parent_controller = tmp;
        parent_controller.SetWindowInvisible();

        view = new MilestonesMenu(this);
        view.setVisible(true);

        RetrievePopulation();

    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        String command = evt.getActionCommand();
        if (focus) {
            switch (command) {

                case "View Tasks":
                    System.out.println("View Tasks");
                    TasksMenuController tmp = new TasksMenuController(this);
                    break;
                case "Delete Milestone":
                    try {
                        view.GetSelectedMilestone();
                        int asd = milestone_list.get(view.getLastSelected()).getId();
                        if (asd < 0) {
                            throw new Exception("No Milestone Selected");
                        }
                        MilestonesController.DeleteById(asd);
                        RetrievePopulation();

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                case "Edit Milestone":
                    try {
                        add_view = new AddMilestoneMenu(this);
                        add_view.ShowSelectedMilestone();
                        add_view.setVisible(true);
                        add_view.SetActionCommand(false);
                        ToggleFocus();
                    } catch (Exception e) {
                        System.out.println(e);
                        add_view.setVisible(false);
                        add_view.dispose();
                    }
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
        } else {

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

                    Milestone EditedMilestone = add_view.GetMilestone();
                    EditedMilestone.setId(milestone_list.get(view.getLastSelected()).getId());
                    MilestonesController.Update(EditedMilestone);

                    ToggleFocus();
                    RetrievePopulation();
                    break;

            }
        }
    }

    public void RetrievePopulation() {
        milestone_list = view.ShowPopulation();
    }

    public Milestone GetSelectedMilestone() {
        Milestone milestone = view.GetSelectedMilestone();
        return milestone;

    }

    public void ToggleFocus() {
        focus = !focus;
    }

    public void SetWindowInvisible() {
        view.setVisible(false);

    }

    public void SetWindowVisible() {
        view.setVisible(true);
    }

    public void CloseWindow() {
        view.setVisible(false);
        view.dispose();
    }
}
