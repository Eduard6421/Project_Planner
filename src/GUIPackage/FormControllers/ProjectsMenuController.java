package GUIPackage.FormControllers;

import Controllers.ProjectsController;
import GUIPackage.AddProjectMenu;
import GUIPackage.ProjectMenu;
import Models.Project;
import Utils.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

public class ProjectsMenuController implements ActionListener {
    
    private ProjectMenu projectMenu;
    private AddProjectMenu addProjectMenu;
    private LoginMenuController parentController;
    private List<Project> projectsList;

    private boolean focus = true;

    private static final Connection conn = MySQLConnector.getConnection();

    public ProjectsMenuController(LoginMenuController controller) {

        parentController = controller;
        parentController.SetWindowInvisible();

        projectMenu = new ProjectMenu(this);
        projectMenu.setVisible(true);
        RetrievePopulation();

    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        String command = evt.getActionCommand();

        System.out.println(command);

        if (focus) {
            switch (command) {

                case "View Milestones":
                    MilestonesMenuController milestonescMenuController = new MilestonesMenuController(this);
                    break;

                case "Delete Project":
                    try {
                        projectMenu.GetSelectedProject();
                        int project = projectsList.get(projectMenu.getLastSelected()).getId();
                        if (project < 0) {
                            throw new Exception("No Project Selected");
                        }
                        ProjectsController.DeleteById(project);
                        RetrievePopulation();

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    break;

                case "Edit Project":
                    try {
                        addProjectMenu = new AddProjectMenu(this);
                        addProjectMenu.ShowSelectedProject();
                        addProjectMenu.setVisible(true);
                        addProjectMenu.SetActionCommand(false);
                        ToggleFocus();
                    } catch (Exception e) {
                        System.out.println(e);
                        addProjectMenu.setVisible(false);
                        addProjectMenu.dispose();
                    }
                    break;

                case "New Project":
                    addProjectMenu = new AddProjectMenu(this);
                    addProjectMenu.setVisible(true);
                    addProjectMenu.SetActionCommand(true);
                    ToggleFocus();
                    break;

                case "Log Out":
                    projectMenu.setVisible(false);
                    projectMenu.dispose();
                    parentController.SetWindowVisible();
            }
        } else {
            switch (command) {
                case "Exit":
                    addProjectMenu.setVisible(false);
                    addProjectMenu.dispose();
                    ToggleFocus();
                    RetrievePopulation();
                    break;

                case "Insert":
                    addProjectMenu.setVisible(false);
                    addProjectMenu.dispose();
                    Project new_project = addProjectMenu.GetProject();
                    ProjectsController.Create(new_project);
                    ToggleFocus();
                    RetrievePopulation();

                    break;

                case "Edit":
                    addProjectMenu.setVisible(false);
                    addProjectMenu.dispose();
                    Project EditedProject = addProjectMenu.GetProject();
                    EditedProject.setId(projectsList.get(projectMenu.getLastSelected()).getId());
                    ProjectsController.Update(EditedProject);
                    ToggleFocus();
                    RetrievePopulation();
                    break;
            }
        }
    }

    public final void RetrievePopulation() {
        projectsList = projectMenu.ShowPopulation();
    }

    public Project GetSelectedProject() {
        Project project = projectMenu.GetSelectedProject();
        return project;
    }

    public void ToggleFocus() {
        focus = !focus;
    }

    public void SetWindowInvisible() {
        projectMenu.setVisible(false);
    }

    public void SetWindowVisible() {
        projectMenu.setVisible(true);
    }

    public void CloseWindow() {
        projectMenu.setVisible(false);
        projectMenu.dispose();
    }
}
