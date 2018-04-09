package Utils;

public class GlobalData {

    //Login status (true if user is logged in)
    private static boolean LoggedIn;

    //Role informations
    private static String RoleTitle;
    
    //User informations
    private static int UserId;
    private static String Username;

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
}
