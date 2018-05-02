package GUIPackage.FormControllers;

import Controllers.TasksController;
import Controllers.UsersController;
import GUIPackage.AddProjectMenu;
import GUIPackage.AddTaskMenu;
import GUIPackage.TasksMenu;
import Models.Project;
import Models.Task;
import Models.User;
import Utils.MySQLConnector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class TasksMenuController implements ActionListener {
    
    private TasksMenu view;
    private MilestonesMenuController parentController;
    private AddTaskMenu addTask;
    private static final Connection conn = MySQLConnector.getConnection();
    private List<Task> task_list;
    private boolean focus = true;

    public TasksMenuController(MilestonesMenuController tmp) {
        parentController = tmp;
        parentController.SetWindowInvisible();

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
                        addTask = new AddTaskMenu(this);
                        addTask.FillDeveloperComboBox();
                        addTask.ShowSelectedTask();
                        addTask.setVisible(true);
                        addTask.SetActionCommand(false);
                        ToggleFocus();
                    } catch (Exception e) {
                        System.out.println(e);
                        addTask.setVisible(false);
                        addTask.dispose();
                    }
                    break;
                case "New Task":
                    addTask = new AddTaskMenu(this);
                    addTask.setVisible(true);
                    addTask.SetActionCommand(true);
                    addTask.FillDeveloperComboBox();
                    ToggleFocus();
                    break;

                default:
                    view.setVisible(false);
                    view.dispose();
                    parentController.SetWindowVisible();
                    parentController.RetrievePopulation();
                    System.out.println("Exit");

            }
        } else {
            switch (command) {
                case "Exit":
                    addTask.setVisible(false);
                    addTask.dispose();
                    ToggleFocus();
                    RetrievePopulation();
                    break;

                case "Insert":
                    addTask.setVisible(false);
                    addTask.dispose();
                    Task new_task = addTask.GetTask();
                    TasksController.Create(new_task);
                    ToggleFocus();
                    RetrievePopulation();

                    break;

                case "Edit":
                    addTask.setVisible(false);
                    addTask.dispose();

                    Task EditedTask = addTask.GetTask();
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
    
    public List<User> GetDevelopers() {
        
        List<User> developers = UsersController.GetAll();
        
        return developers;
    }
}
