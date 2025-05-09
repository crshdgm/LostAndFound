package com.example.lostandfound;

/**
 * POJO representing a single report in your Realtime Database.
 * Includes fields for description, imageUrl, approval flag, owner’s name, and owner’s ID.
 */
public class Report {
    private String description;
    private boolean approved;
    private String owner;
    private String schoolId;

    /** No-arg constructor required by Firebase */
    public Report() { }

    /** All-args constructor (use if instantiating in code) */
    public Report(String description,
                  String imageUrl,
                  boolean approved,
                  String owner,
                  String schoolId) {
        this.description = description;
        this.approved    = approved;
        this.owner       = owner;
        this.schoolId     = schoolId;
    }

    /** Getter for report description */
    public String getDescription() {
        return description;
    }

    /** Getter for approval flag */
    public boolean isApproved() {
        return approved;
    }

    /** Getter for owner’s display name */
    public String getOwner() {
        return owner;
    }

    /** Getter for owner’s unique ID */
    public String getSchoolId() {
        return schoolId;
    }

    // Optional setters if you need to modify after fetching

    public void setDescription(String description) {
        this.description = description;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setOwnerName(String ownerName) {
        this.owner = ownerName;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
}
