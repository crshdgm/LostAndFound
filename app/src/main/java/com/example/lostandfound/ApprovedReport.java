package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

public class ApprovedReport extends AppCompatActivity {
    private String itemName;
    private String locationFound;
    private String date;
    private String claimedBy;
    private int imageResourceId;


    public ApprovedReport(String itemName, String locationFound, String date, String claimedBy, int imageResourceId) {
        this.itemName = itemName;
        this.locationFound = locationFound;
        this.date = date;
        this.claimedBy = claimedBy;
        this.imageResourceId = imageResourceId;
    }


    public String getItemName() {
        return itemName;
    }

    public String getLocationFound() {
        return locationFound;
    }

    public String getDate() {
        return date;
    }

    public String getClaimedBy() {
        return claimedBy;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}

