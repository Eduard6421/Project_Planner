package Models;

public class Role extends Entity {

    private String Title;

    public Role(int id, String title) {
        Id = id;
        Title = title;
    }

    public Role(String title) {
        Title = title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTitle() {
        return Title;
    }
}
