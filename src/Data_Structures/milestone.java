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
