package GUIPackage.FormControllers;

import Controllers.TasksController;
import GUIPackage.AddProjectMenu;
import GUIPackage.AddTaskMenu;
import GUIPackage.TasksMenu;
import Models.Project;
import Models.Task;
import Utils.MySQLConnector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class TasksMenuController implements ActionListener {
    
    private TasksMenu view;
    private MilestonesMenuController parent_controller;
    private AddTaskMenu add_view;
    private static final Connection conn = MySQLConnector.getConnection();
    private List<Task> task_list;
    private boolean focus = true;

    public TasksMenuController(MilestonesMenuController tmp) {
        parent_controller = tmp;
        parent_controller.SetWindowInvisible();

        view = new TasksMenu(this);
        view.setVisible(true);
        RetrievePopulation();

    }

    private void RetrievePopulation() {

        task_list = view.ShowPopulation();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        String command = evt.getActionCommand();
        if (focus) {
            switch (command) {

                case "Delete Task":
                    try {
                        view.GetSelectedTask();
                        int asd = task_list.get(view.getLastSelected()).getId();

                        if (asd < 0) {
                            throw new Exception("No Task Selected");
                        }

                        TasksController.DeleteById(asd);
                        RetrievePopulation();

                    } catch (Exception e) {
                        System.out.println(e);
                    }

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
                    EditedTask.setId(task_list.get(view.getLastSelected()).getId());
                    TasksController.Update(EditedTask);

                    ToggleFocus();
                    RetrievePopulation();
                    break;
            }
        }
    }

    public Task GetSelectedTask() {

        Task task = view.GetSelectedTask();
        return task;

    }

    public void ToggleFocus() {
        focus = !focus;
    }
}
