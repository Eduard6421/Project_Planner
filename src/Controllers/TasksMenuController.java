/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIPackage.AddProjectMenu;
import GUIPackage.AddTaskMenu;
import GUIPackage.TaskMenu;
import Models.Project;
import Models.Task;
import Utils.MySQLConnector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 *
 * @author Eduard
 */
public class TasksMenuController implements ActionListener {

    private TaskMenu view;
    private MilestonesMenuController parent_controller;
    private AddTaskMenu add_view;
    private static final Connection conn = MySQLConnector.getConnection();

    private boolean focus = true;

    public TasksMenuController(MilestonesMenuController tmp) {
        parent_controller = tmp;
        parent_controller.SetWindowInvisible();

        view = new TaskMenu(this);
        view.setVisible(true);
        RetrievePopulation();

    }

    private void RetrievePopulation() {
        view.ShowPopulation();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        String command = evt.getActionCommand();
        if (focus) {
            switch (command) {

                case "Delete Task":
                    System.out.println("Delete Task");

                    break;
                case "Edit Task":
                    try {
                        add_view = new AddTaskMenu(this);
                        add_view.ShowSelectedTask();
                        add_view.setVisible(true);
                        add_view.SetActionCommand(false);
                        ToggleFocus();
                    } catch (Exception e) {
                        System.out.println(e);
                        add_view.setVisible(false);
                        add_view.dispose();
                    }
                    break;
                case "New Task":
                    add_view = new AddTaskMenu(this);
                    add_view.setVisible(true);
                    add_view.SetActionCommand(true);
                    ToggleFocus();
                    break;

                default:
                    view.setVisible(false);
                    view.dispose();
                    parent_controller.SetWindowVisible();
                    parent_controller.RetrievePopulation();
                    System.out.println("Exit");

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
                    Task new_task = add_view.GetTask();
                    TasksController.Create(new_task);
                    ToggleFocus();
                    RetrievePopulation();

                    break;

                case "Edit":
                    add_view.setVisible(false);
                    add_view.dispose();

                    Task EditedTask = add_view.GetTask();
                    EditedTask.setId(view.getLastSelected());
                    TasksController.Update(EditedTask);

                    ToggleFocus();
                    RetrievePopulation();
                    break;
            }
        }
    }

    public Task GetSelectedTask(){
 
    Task task = view.GetSelectedTask();
    return task;
            
    }

    public void ToggleFocus() {
        focus = !focus;
    }

}
