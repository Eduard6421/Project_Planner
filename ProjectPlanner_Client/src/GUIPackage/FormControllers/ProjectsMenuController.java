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

public class ProjectsMenuController implements ActionListener {
    
    private ProjectsMenu projectMenu;
    private AddProjectMenu addProjectMenu;
    private LoginMenuController parentController;
    private List<Project> projectsList;

    private boolean focus = true;


    public ProjectsMenuController(LoginMenuController controller) {

        parentController = controller;
        parentController.setWindowInvisible();

        projectMenu = new ProjectsMenu(this);
        projectMenu.setVisible(true);
        retrievePopulation();   
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
                        ProjectsController.deleteById(projectId);
                        retrievePopulation();

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
                        toggleFocus();
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
                    toggleFocus();
                    break;

                case "Log Out":
                    projectMenu.setVisible(false);
                    projectMenu.dispose();
                    parentController.setWindowVisible();
                    break;
                case "Get All Projects":
                    retrievePopulation();
                    break;
                case "Get Projects Between":
                    retrievePopulationBetweenDates();
                    break;
            }
        } else {
            switch (command) {
                case "Exit":
                    addProjectMenu.setVisible(false);
                    addProjectMenu.dispose();
                    toggleFocus();
                    retrievePopulation();
                    break;

                case "Insert":
                    addProjectMenu.setVisible(false);
                    addProjectMenu.dispose();
                    Project newProject = addProjectMenu.GetProject();
                    if (newProject != null) {
                        ProjectsController.create(newProject);   
                    }
                    toggleFocus();
                    retrievePopulation();

                    break;

                case "Edit":
                    addProjectMenu.setVisible(false);
                    addProjectMenu.dispose();
                    Project EditedProject = addProjectMenu.GetProject();
                    EditedProject.setId(projectsList.get(projectMenu.getLastSelectedId()).getId());
                    ProjectsController.update(EditedProject);
                    toggleFocus();
                    retrievePopulation();
                    break;
            }
        }
    }

    public final void retrievePopulation() {
        projectsList = projectMenu.ShowPopulation();
    }
    
    public final void retrievePopulationBetweenDates() {
        projectsList = projectMenu.ShowPopulationBetweenDates();
    }

    public Project getSelectedProject() {
        Project project = projectMenu.GetSelectedProject();
        return project;
    }

    public void toggleFocus() {
        focus = !focus;
    }

    public void setWindowInvisible() {
        projectMenu.setVisible(false);
    }

    public void setWindowVisible() {
        projectMenu.setVisible(true);
    }

    public void closeWindow() {
        projectMenu.setVisible(false);
        projectMenu.dispose();
    }
    
    public List<User> getManagers() {
        
        List<User> managers = UsersController.getManagers();
        
        return managers;
    }


}
