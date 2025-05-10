package com.example.lostandfound;

public class User {
    private String fullName;
    private String email;
    private String dateOfBirth;
    private String phoneNumber;
    private boolean blocked;
    private String uid;
    private String schoolId;

    public User() {}          // required

    public String getFullName()   { return fullName; }

    public String getEmail()      { return email; }
    public String getDateOfBirth(){ return dateOfBirth; }
    public String getPhoneNumber(){ return phoneNumber; }
    public boolean isBlocked()    { return blocked; }
    public String getUid()        { return uid; }

    public void setUid(String uid){ this.uid = uid; }
    public void setBlocked(boolean b){ this.blocked = b; }

    public String getSchoolId()        { return schoolId; }
    public void   setSchoolId(String s){ this.schoolId = s; }
}

