/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataStructures.project;
import GUIPackage.AddProjectMenu;
import GUIPackage.ProjectMenu;
import Models.Project;
import Utils.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

public class ProjectsMenuController implements ActionListener {

    private ProjectMenu view;
    private AddProjectMenu add_view;
    private LoginMenuController parent_controller;
    private List<Project> project_list;

    private boolean focus = true;

    private static final Connection conn = MySQLConnector.getConnection();

    public ProjectsMenuController(LoginMenuController controller) {

        parent_controller = controller;
        parent_controller.SetWindowInvisible();

        view = new ProjectMenu(this);
        view.setVisible(true);
        RetrievePopulation();

    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        String command = evt.getActionCommand();

        System.out.println(command);

        if (focus) {
            switch (command) {

                case "View Milestones":
                    MilestonesMenuController tmp = new MilestonesMenuController(this);
                    break;

                case "Delete Project":
                    try {
                        view.GetSelectedProject();
                        int asd = project_list.get(view.getLastSelected()).getId();
                        if (asd < 0) {
                            throw new Exception("No Project Selected");
                        }
                        ProjectsController.DeleteById(asd);
                        RetrievePopulation();

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    break;

                case "Edit Project":
                    try {
                        add_view = new AddProjectMenu(this);
                        add_view.ShowSelectedProject();
                        add_view.setVisible(true);
                        add_view.SetActionCommand(false);
                        ToggleFocus();
                    } catch (Exception e) {
                        System.out.println(e);
                        add_view.setVisible(false);
                        add_view.dispose();
                    }
                    break;

                case "New Project":
                    add_view = new AddProjectMenu(this);
                    add_view.setVisible(true);
                    add_view.SetActionCommand(true);
                    ToggleFocus();
                    break;

                case "Log Out":
                    view.setVisible(false);
                    view.dispose();
                    parent_controller.SetWindowVisible();

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
                    Project new_project = add_view.GetProject();
                    ProjectsController.Create(new_project);
                    ToggleFocus();
                    RetrievePopulation();

                    break;

                case "Edit":
                    add_view.setVisible(false);
                    add_view.dispose();
                    Project EditedProject = add_view.GetProject();
                    EditedProject.setId(project_list.get(view.getLastSelected()).getId());
                    ProjectsController.Update(EditedProject);
                    ToggleFocus();
                    RetrievePopulation();
                    break;

            }
        }
    }

    public final void RetrievePopulation() {
        project_list = view.ShowPopulation();
    }

    public Project GetSelectedProject() {
        Project project = view.GetSelectedProject();
        return project;

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
