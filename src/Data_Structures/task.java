/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Structures;

/**
 *
 * @author Eduard
 */

import java.util.Date;

public class task {

  
    private int Id;
    private String Title;
    private int Priority;
    private String Description;
    private Date Start_date;
    private Date End_date;
    private int Assigned_Milestone;
    
    
      public int getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public int getPriority() {
        return Priority;
    }

    public String getDescription() {
        return Description;
    }

    public Date getStart_date() {
        return Start_date;
    }

    public Date getEnd_date() {
        return End_date;
    }

    public int getAssigned_Milestone() {
        return Assigned_Milestone;
    }
    
    
    
}
