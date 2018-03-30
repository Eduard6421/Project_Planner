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
public class Task extends Entity {

    private String Title;
    private Priority Priority;
    private String Description;
    private Date StartDate;
    private Date EndDate;
    private int MilestoneId;
    private int AssignedToId;

    public Task(int id, String title, Priority priority, String description, Date startDate, Date endDate, int milestoneId, int assignedToId) {
        Id = id;
        Title = title;
        Priority = priority;
        Description = description;
        StartDate = startDate;
        EndDate = endDate;
        MilestoneId = milestoneId;
        AssignedToId = assignedToId;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setPriority(Priority priority) {
        Priority = priority;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public void setMilestoneId(int milestoneId) {
        MilestoneId = milestoneId;
    }

    public void setAssignedToId(int assignedToId) {
        AssignedToId = assignedToId;
    }

    public String getTitle() {
        return Title;
    }

    public Priority getPriority() {
        return Priority;
    }

    public String getDescription() {
        return Description;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public int getMilestoneId() {
        return MilestoneId;
    }

    public int getAssignedToId() {
        return AssignedToId;
    }
}
