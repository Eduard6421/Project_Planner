/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_planner;

import java.util.Date;

/**
 *
 * @author Eduard
 */
public class project {
 
    private  String project_name;
    private  String client_name;
    private Date start_date;
    private Date end_date;
    private double project_budget;
    
    
    
    public project(String project_name,String client_name,Date start_date,Date end_date,int project_budget)
    {
     this.project_name = project_name;
     this.client_name  = client_name;
     this.start_date   =  start_date;
     this.end_date     = end_date;
     this.project_budget = project_budget;
        
    }
    
    
    
}
