package com.example.lostandfound;

public class Client {
    private String fullName;
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String password;
    private String schoolId;

    // 1) Empty constructor required by Firebase
    public Client() { }

    // 2) Full constructor for convenience
    public Client(String fullName,
                  String dateOfBirth,
                  String phoneNumber,
                  String email,
                  String password,
                  String schoolId) {
        this.fullName    = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email       = email;
        this.password    = password;
        this.schoolId   = schoolId;
    }

    // 3) Getters & setters for each field
    public String getFullName()      { return fullName; }
    public void   setFullName(String fullName) { this.fullName = fullName; }

    public String getDateOfBirth()   { return dateOfBirth; }
    public void   setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPhoneNumber()   { return phoneNumber; }
    public void   setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail()         { return email; }
    public void   setEmail(String email) { this.email = email; }

    public String getPassword()      { return password; }
    public void   setPassword(String password) { this.password = password; }

    public String getSchoolId() { return schoolId; }
    public void   setSchoolId(String s) { this.schoolId = s;}
    }
