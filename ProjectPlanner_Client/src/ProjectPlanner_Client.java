import Client.Client;
import GUIPackage.FormControllers.LoginMenuController;
import Controllers.ProjectsController;
import GUIPackage.FormControllers.ProjectsMenuController;
import Utils.*;
import java.io.IOException;

public class ProjectPlanner_Client {
    
    public static void main(String[] args) throws IOException {
        
        LoginMenuController controller = new LoginMenuController();
        
        Client.startClient();
    }
}
