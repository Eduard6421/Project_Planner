package Models;

import java.util.Date;

public class Project extends Entity {

    private int ManagerId;
    private String Title;
    private String ClientName;
    private Date StartDate;
    private Date EndDate;
    private double Budget;
    private String Description;
    
    public Project() {}
    
    public Project (int id, int managerId, String title, String clientName, Date startDate, Date endDate, double budget, String description) {
        Id = id;
        ManagerId = managerId;
        Title = title;
        ClientName = clientName;
        StartDate = startDate;
        EndDate = endDate;
        Budget = budget;
        Description = description;
    }
    
    public Project (int managerId, String title, String clientName, Date startDate, Date endDate, double budget, String description) {
        ManagerId = managerId;
        Title = title;
        ClientName = clientName;
        StartDate = startDate;
        EndDate = endDate;
        Budget = budget;
        Description = description;
    }

    public void setManagerId(int managerId) {
        ManagerId = managerId;
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

    public void setDescription(String description) {
        Description = description;
    }

    public int getManagerId() {
        return ManagerId;
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

    public String getDescription() {
        return Description;
    }
}
