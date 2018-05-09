package Models;

import java.io.Serializable;

public class Entity implements Serializable {
    
    protected int Id;
    
    public void setId(int id) {
        Id = id;
    }
    
    public int getId() {
        return Id;
    }
}
