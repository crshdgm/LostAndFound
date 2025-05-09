package com.example.lostandfound;

public class Item {
    // 1) Fields
    private String name;
    private String description;
    private String dateFound;
    private String owner;
    private String imageUrl;     // ← add this

    // 2) No-arg constructor required by Firebase
    public Item() { }

    // 3) All-args constructor (now including imageUrl)
    public Item(String name,
                String description,
                String dateFound,
                String owner,
                String imageUrl)   // ← include here
    {
        this.name        = name;
        this.description = description;
        this.dateFound   = dateFound;
        this.owner       = owner;
        this.imageUrl    = imageUrl;
    }

    // 4) Getters (used by Firebase)
    public String getName()        { return name; }
    public String getDescription() { return description; }
    public String getDateFound()   { return dateFound; }
    public String getOwner()       { return owner; }
    public String getImageUrl()    { return imageUrl; }  // ← add getter

    // 5) (Optional) Setters
    public void setName(String name)               { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setDateFound(String dateFound)     { this.dateFound = dateFound; }
    public void setOwner(String owner)             { this.owner = owner; }
    public void setImageUrl(String imageUrl)       { this.imageUrl = imageUrl; }  // ← setter
}
