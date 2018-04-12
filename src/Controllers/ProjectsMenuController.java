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

public class ProjectsMenuController implements ActionListener {

    private ProjectMenu view;
    private AddProjectMenu add_view;
    private LoginMenuController parent_controller;

    private boolean focus = true;

    private static final Connection conn = MySQLConnector.getConnection();

    public ProjectsMenuController(LoginMenuController controller) {

        parent_controller = controller;
        parent_controller.SetWindowInvisible();

        view = new ProjectMenu(this);
        view.setVisible(true);
        RetrievePopulation();

    }

    public void RetrievePopulation() {
        view.ShowPopulation();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        String command = evt.getActionCommand();

        System.out.println(command);

        if (focus) {
            switch (command) {

                case "View Milestones":
                    System.out.println("Milestones");
                    MilestonesMenuController tmp = new MilestonesMenuController(this);

                    break;
                case "Delete Project":
                    System.out.println("Delete Project");
                    break;
                case "Edit Project":
                    System.out.println("Edit Project");
                    ToggleFocus();
                    add_view = new AddProjectMenu(this);
                    add_view.setVisible(true);
                    break;
                case "New Project":
                    System.out.println("New Project");
                    ToggleFocus();
                    add_view = new AddProjectMenu(this);
                    add_view.setVisible(true);

                    break;

                case "Edit":
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
                    System.out.println("Exit");
                    break;

                case "Insert":

                    Project new_project = add_view.GetProject();
                    ProjectsController.Create(new_project);

                    ToggleFocus();
                    RetrievePopulation();
                    add_view.setVisible(false);
                    add_view.dispose();

                    break;

            }
        }
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
