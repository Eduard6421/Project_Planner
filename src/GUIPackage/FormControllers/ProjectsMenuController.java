package GUIPackage.FormControllers;

import Controllers.ProjectsController;
import Controllers.UsersController;
import GUIPackage.AddProjectMenu;
import GUIPackage.ProjectsMenu;
import Models.Project;
import Models.User;
import Utils.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;

public class ProjectsMenuController implements ActionListener {
    
    private ProjectsMenu projectMenu;
    private AddProjectMenu addProjectMenu;
    private LoginMenuController parentController;
    private List<Project> projectsList;

    private boolean focus = true;

    private static final Connection conn = MySQLConnector.getConnection();

    public ProjectsMenuController(LoginMenuController controller) {

        parentController = controller;
        parentController.SetWindowInvisible();

        projectMenu = new ProjectsMenu(this);
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
                    try {
                        int projectId = projectsList.get(projectMenu.getLastSelectedId()).getId();
                        if (projectId < 0) {
                            throw new Exception("No Project Selected");
                        }
                        GlobalData.setProjectId(projectId);
                        Project selectedProject = projectMenu.GetSelectedProject();
                        GlobalData.setProjectTitle(selectedProject.getTitle());
                        
                        MilestonesMenuController milestonescMenuController = new MilestonesMenuController(this);

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                    
                case "Delete Project":
                    try {
                        projectMenu.GetSelectedProject();
                        int projectId = projectsList.get(projectMenu.getLastSelectedId()).getId();
                        if (projectId < 0) {
                            throw new Exception("No Project Selected");
                        }
                        ProjectsController.DeleteById(projectId);
                        RetrievePopulation();

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    break;

                case "Edit Project":
                    try {
                        addProjectMenu = new AddProjectMenu(this);
                        addProjectMenu.FillManagerComboBox();
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
                    addProjectMenu.FillManagerComboBox();
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
                    EditedProject.setId(projectsList.get(projectMenu.getLastSelectedId()).getId());
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
    
    public List<User> GetManagers() {
        
        List<User> managers = UsersController.GetManagers();
        
        return managers;
    }


}
