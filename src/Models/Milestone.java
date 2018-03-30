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
public class Milestone extends Entity {
    
    private int ProjectId;
    private String Title;
    private Date StartDate;
    private Date EndDate;
    private String Description;
    
    public Milestone(int id, int projectId, String title, Date startDate, Date endDate, String description) {
        Id = id;
        ProjectId = projectId;
        Title = title;
        StartDate = startDate;
        EndDate = endDate;
        Description = description;
    }
    
    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }
    
    public void setTitle(String title) {
        Title = title;
    }
    
    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }
    
    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }
    
    public void setDescription(String description) {
        Description = description;
    }
    
    public int getProjectId() {
        return ProjectId;
    }
    
    public String getTitle() {
        return Title;
    }
    
    public Date getStartDate() {
        return StartDate;
    }
    
    public Date getEndDate() {
        return EndDate;
    }
    
    public String detDescription() {
        return Description;
    }
}
