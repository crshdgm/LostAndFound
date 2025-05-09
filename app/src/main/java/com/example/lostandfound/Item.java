package com.example.lostandfound;

public class Item {
    private String name;
    private String description;
    private String dateFound;
    private String owner;
    private String imageUrl;
    private String schoolId;

    // no-arg constructor
    public Item() { }

    // all-args constructor (now including schoolId)
    public Item(String name,
                String description,
                String dateFound,
                String owner,
                String imageUrl,
                String schoolId) {
        this.name        = name;
        this.description = description;
        this.dateFound   = dateFound;
        this.owner       = owner;
        this.imageUrl    = imageUrl;
        this.schoolId    = schoolId;
    }

    // getters Firebase will use
    public String getName()        { return name; }
    public String getDescription() { return description; }
    public String getDateFound()   { return dateFound; }
    public String getOwner()       { return owner; }
    public String getImageUrl()    { return imageUrl; }
    public String getSchoolId()    { return schoolId; }  // ← NEW!

    // setters (optional)
    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
}
