/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Structures;

import java.util.Date;

/**
 *
 * @author Eduard
 */
public class project {


    
        
    private int Id;
    private String Project_Name;
    private String Client_Name;
    private Date Start_date;
    private Date End_date;
    private double Budget;
    
    public int getId() {
        return Id;
    }

    public String getProject_Name() {
        return Project_Name;
    }

    public String getClient_Name() {
        return Client_Name;
    }

    public Date getStart_date() {
        return Start_date;
    }

    public Date getEnd_date() {
        return End_date;
    }

    public double getBudget() {
        return Budget;
    }

    
    public project(int id,String project_name,String client_name,Date start_date,Date end_date,double budget)
    {
     this.Id = id;
     this.Project_Name = project_name;
     this.Client_Name = client_name;
     this.Start_date = start_date;
     this.End_date = end_date;
     this.Budget = budget;
   
    }
    
    public void swap_values(int id,String project_name,String client_name,Date start_date,Date end_date,double budget)
    {
        
     
        
        
    }
    
   
}
