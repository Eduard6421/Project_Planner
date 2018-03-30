/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author Stefan
 */
public class Project extends Entity {
    
    private String Title;
    private String ClientName;
    private Date StartDate;
    private Date EndDate;
    private double Budget;
    
    public Project (int id, String title, String clientName, Date startDate, Date endDate, double budget) {
        Id = id;
        Title = title;
        ClientName = clientName;
        StartDate = startDate;
        EndDate = endDate;
        Budget = budget;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public void setBudget(double budget) {
        Budget = budget;
    }

    public String getTitle() {
        return Title;
    }

    public String getClientName() {
        return ClientName;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public double getBudget() {
        return Budget;
    }
}
