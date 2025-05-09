package com.example.lostandfound;

public class Item {
    private String requestNumber, name, schoolId, dateFound, description;
    private boolean approved;
    public Item() { }
    public Item(String requestNumber,
                String name,
                String schoolId,
                String dateFound,
                String description) {
        this.requestNumber = requestNumber;
        this.name          = name;
        this.schoolId      = schoolId;
        this.dateFound     = dateFound;
        this.description   = description;
    }
    public String getRequestNumber() { return requestNumber; }
    public String getName()          { return name; }
    public String getSchoolId()      { return schoolId; }
    public String getDateFound()     { return dateFound; }
    public String getDescription()   { return description; }
    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }
}

