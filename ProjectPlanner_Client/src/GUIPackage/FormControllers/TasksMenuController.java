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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class TasksMenuController implements ActionListener {
    
    private TasksMenu tasksMenu;
    private MilestonesMenuController parentController;
    private AddTaskMenu addTask;
    private List<Task> tasksList;
    private boolean focus = true;

    public TasksMenuController(MilestonesMenuController tmp) {
        parentController = tmp;
        parentController.setWindowInvisible();

        tasksMenu = new TasksMenu(this);
        tasksMenu.setVisible(true);
        retrievePopulation();

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

                        TasksController.deleteById(asd);
                        retrievePopulation();

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
                        toggleFocus();
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
                    toggleFocus();
                    break;
                case "Finish Task":
                    tryMarkTaskAsFinished();
                    retrievePopulation();
                    break;
                case "Mark Task as Open":
                    tryMarkTaskAsOpen();
                    retrievePopulation();
                    break;
                default:
                    tasksMenu.setVisible(false);
                    tasksMenu.dispose();
                    parentController.setWindowVisible();
                    parentController.retrievePopulation();
                    System.out.println("Exit");

            }
        } else {
            switch (command) {
                case "Exit":
                    addTask.setVisible(false);
                    addTask.dispose();
                    toggleFocus();
                    retrievePopulation();
                    break;

                case "Insert":
                    addTask.setVisible(false);
                    addTask.dispose();
                    Task newTask = addTask.GetTask();
                    if (newTask != null) {
                        TasksController.create(newTask);   
                    }
                    toggleFocus();
                    retrievePopulation();

                    break;

                case "Edit":
                    addTask.setVisible(false);
                    addTask.dispose();

                    Task EditedTask = addTask.GetTask();
                    EditedTask.setId(tasksList.get(tasksMenu.getLastSelected()).getId());
                    TasksController.update(EditedTask);

                    toggleFocus();
                    retrievePopulation();
                    break;
            }
        }
    }
    
    private void retrievePopulation() {

        tasksList = tasksMenu.ShowPopulation();
    }

    public Task getSelectedTask() {

        Task task = tasksMenu.GetSelectedTask();
        return task;

    }

    public void toggleFocus() {
        focus = !focus;
    }
    
    public List<User> getDevelopers() {
        
        List<User> developers = UsersController.getAll();
        
        return developers;
    }
    
    public void tryMarkTaskAsFinished() {

        Task selectedTask = tasksMenu.GetSelectedTask();
        
        Boolean canBeFinished = true;
        
        if (selectedTask.getFinished() == true) {
            canBeFinished = false;
        }
        
        if (GlobalData.getRoleTitle().equals("Employee") && GlobalData.getUserId() != selectedTask.getAssignedToId()) {
            canBeFinished = false;
        }
        
        if (canBeFinished) {
            TasksController.finishTaskById(selectedTask.getId()); 
        }
        else {
            System.out.println("Can't finish selected task");
        }    
    }
    
    public void tryMarkTaskAsOpen() {
        Task selectedTask = tasksMenu.GetSelectedTask();
        
        Boolean canBeOpened = true;
        
        if (selectedTask.getFinished() == false) {
            canBeOpened = false;
        }
        
        if (GlobalData.getRoleTitle().equals("Employee") && GlobalData.getUserId() != selectedTask.getAssignedToId()) {
            canBeOpened = false;
        }
        
        if (canBeOpened) {
            TasksController.openTaskById(selectedTask.getId()); 
        }
        else {
            System.out.println("Can't open selected task");
        } 
    }
}
