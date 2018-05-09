package Utils;

public class GlobalData {

    //Login status (true if user is logged in)
    private static boolean LoggedIn;

    //Role informations
    private static String RoleTitle;
    
    //User informations
    private static int UserId;
    private static String Username;
    
    //Window state (project, milestone or task Id)
    private static int ProjectId;
    private static String ProjectTitle;
    private static int MilestoneId;
    private static String MilestoneTitle;
    private static int TaskId;
    private static String TaskTitle;

    public static void setLoggedIn(boolean loggedIn) {
        LoggedIn = loggedIn;
    }

    public static void setRoleTitle(String roleTitle) {
        RoleTitle = roleTitle;
    }

    public static void setUserId(int userId) {
        UserId = userId;
    }

    public static void setUsername(String username) {
        Username = username;
    }

    public static void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public static void setProjectTitle(String projectTitle) {
        ProjectTitle = projectTitle;
    }

    public static void setMilestoneId(int milestoneId) {
        MilestoneId = milestoneId;
    }

    public static void setMilestoneTitle(String milestoneTitle) {
        MilestoneTitle = milestoneTitle;
    }

    public static void setTaskId(int taskId) {
        TaskId = taskId;
    }

    public static void setTaskTitle(String taskTitle) {
        TaskTitle = taskTitle;
    }

    public static boolean isLoggedIn() {
        return LoggedIn;
    }

    public static String getRoleTitle() {
        return RoleTitle;
    }

    public static int getUserId() {
        return UserId;
    }

    public static String getUsername() {
        return Username;
    }

    public static int getProjectId() {
        return ProjectId;
    }

    public static String getProjectTitle() {
        return ProjectTitle;
    }

    public static int getMilestoneId() {
        return MilestoneId;
    }

    public static String getMilestoneTitle() {
        return MilestoneTitle;
    }

    public static int getTaskId() {
        return TaskId;
    }

    public static String getTaskTitle() {
        return TaskTitle;
    }
}
