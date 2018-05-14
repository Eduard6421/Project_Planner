package GUIPackage.FormControllers;

import Controllers.TasksController;
import Controllers.UsersController;
import GUIPackage.AddProjectMenu;
import GUIPackage.AddTaskMenu;
import GUIPackage.TasksMenu;
import Models.Project;
import Models.Task;
import Models.User;
import Utils.GlobalData;
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
                case "Finish Task":
                    TryMarkTaskAsFinished();
                    RetrievePopulation();
                    break;
                case "Mark Task as Open":
                    TryMarkTaskAsOpen();
                    RetrievePopulation();
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
                    Task newTask = addTask.GetTask();
                    if (newTask != null) {
                        TasksController.Create(newTask);   
                    }
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
    
    private void RetrievePopulation() {

        tasksList = tasksMenu.ShowPopulation();
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
    
    public void TryMarkTaskAsFinished() {

        Task selectedTask = tasksMenu.GetSelectedTask();
        
        Boolean canBeFinished = true;
        
        if (selectedTask.getFinished() == true) {
            canBeFinished = false;
        }
        
        if (GlobalData.getRoleTitle().equals("Employee") && GlobalData.getUserId() != selectedTask.getAssignedToId()) {
            canBeFinished = false;
        }
        
        if (canBeFinished) {
            TasksController.FinishTaskById(selectedTask.getId()); 
        }
        else {
            System.out.println("Can't finish selected task");
        }    
    }
    
    public void TryMarkTaskAsOpen() {
        Task selectedTask = tasksMenu.GetSelectedTask();
        
        Boolean canBeOpened = true;
        
        if (selectedTask.getFinished() == false) {
            canBeOpened = false;
        }
        
        if (GlobalData.getRoleTitle().equals("Employee") && GlobalData.getUserId() != selectedTask.getAssignedToId()) {
            canBeOpened = false;
        }
        
        if (canBeOpened) {
            TasksController.OpenTaskById(selectedTask.getId()); 
        }
        else {
            System.out.println("Can't open selected task");
        } 
    }
}
