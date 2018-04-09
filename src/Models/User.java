package Models;

public class User extends Entity {

    private int RoleId;
    private String Username;
    private String Password;
    private String FirstName;
    private String LastName;

    public User(int id, int roleId, String username, String password, String firstName, String lastName) {
        Id = id;
        RoleId = roleId;
        Username = username;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
    }

    public User(int roleId, String username, String password, String firstName, String lastName) {
        RoleId = roleId;
        Username = username;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getRoleId() {
        return RoleId;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }
}
