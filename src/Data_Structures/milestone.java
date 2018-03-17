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


public class milestone {

    public milestone(int Id, String Title, Date Start_date, Date End_Date, String Description) {
        this.Id = Id;
        this.Title = Title;
        this.Start_date = Start_date;
        this.End_Date = End_Date;
        this.Description = Description;
    }

    
    private int Id;
    private int Assigned_Project;
    private String Title;
    private Date Start_date;
    private Date End_Date;
    private String Description;


    public int getId() {
        return Id;
    }

    public int getAssigned_Project() {
        return Assigned_Project;
    }

    public String getTitle() {
        return Title;
    }

    public Date getStart_date() {
        return Start_date;
    }

    public Date getEnd_Date() {
        return End_Date;
    }

    public String getDescription() {
        return Description;
    }
    
    
    
}
