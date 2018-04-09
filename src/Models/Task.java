package Models;

import java.util.Date;

public class Task extends Entity {

    private int MilestoneId;
    private int AssignedToId;
    private int PriorityId;
    private String Title;
    private String Description;
    private Date StartDate;
    private Date EndDate;

    public Task(int id, int milestoneId, int assignedToId, int priorityId, String title, Date startDate, Date endDate, String description) {
        Id = id;
        MilestoneId = milestoneId;
        AssignedToId = assignedToId;
        PriorityId = priorityId;
        Title = title;
        StartDate = startDate;
        EndDate = endDate;
        Description = description;
    }
    
    public Task(int milestoneId, int assignedToId, int priorityId, String title, Date startDate, Date endDate, String description) {
        MilestoneId = milestoneId;
        AssignedToId = assignedToId;
        PriorityId = priorityId;
        Title = title;
        StartDate = startDate;
        EndDate = endDate;
        Description = description;
    }

    public void setMilestoneId(int milestoneId) {
        MilestoneId = milestoneId;
    }

    public void setAssignedToId(int assignedToId) {
        AssignedToId = assignedToId;
    }

    public void setPriorityId(int priorityId) {
        PriorityId = priorityId;
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

    public int getMilestoneId() {
        return MilestoneId;
    }

    public int getAssignedToId() {
        return AssignedToId;
    }

    public int getPriorityId() {
        return PriorityId;
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

    public String getDescription() {
        return Description;
    }
}
