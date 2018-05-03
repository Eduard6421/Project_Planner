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
    
    private TasksMenu tasksMenu;
    private MilestonesMenuController parentController;
    private AddTaskMenu addTask;
    private static final Connection conn = MySQLConnector.getConnection();
    private List<Task> tasksList;
    private boolean focus = true;

    public TasksMenuController(MilestonesMenuController tmp) {
        parentController = tmp;
        parentController.SetWindowInvisible();

        tasksMenu = new TasksMenu(this);
        tasksMenu.setVisible(true);
        RetrievePopulation();

    }

    private void RetrievePopulation() {

        tasksList = tasksMenu.ShowPopulation();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        String command = evt.getActionCommand();
        if (focus) {
            switch (command) {

                case "Delete Task":
                    try {
                        tasksMenu.GetSelectedTask();
                        int asd = tasksList.get(tasksMenu.getLastSelected()).getId();

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
                        addTask.FillPrioritiesComboBox();
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
                    addTask.FillPrioritiesComboBox();
                    ToggleFocus();
                    break;

                default:
                    tasksMenu.setVisible(false);
                    tasksMenu.dispose();
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
                    EditedTask.setId(tasksList.get(tasksMenu.getLastSelected()).getId());
                    TasksController.Update(EditedTask);

                    ToggleFocus();
                    RetrievePopulation();
                    break;
            }
        }
    }

    public Task GetSelectedTask() {

        Task task = tasksMenu.GetSelectedTask();
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
