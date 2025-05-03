package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    Button btnAddItem, btnViewReports, btnApprovedReports, btnManageUsers, btnStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddItem = findViewById(R.id.btnAddItem);
        btnViewReports = findViewById(R.id.btnViewReports);
        btnApprovedReports = findViewById(R.id.btnApprovedReports);
        btnManageUsers = findViewById(R.id.btnManageUsers);
        btnStatistics = findViewById(R.id.btnStatistics);

        btnAddItem.setOnClickListener(view -> startActivity(new Intent(this, AddItemActivity.class)));
        btnViewReports.setOnClickListener(view -> startActivity(new Intent(this, ViewReportsActivity.class)));
        btnApprovedReports.setOnClickListener(view -> startActivity(new Intent(this, ApprovedReportsActivity.class)));
        btnManageUsers.setOnClickListener(view -> startActivity(new Intent(this, ManageUsersActivity.class)));
        btnStatistics.setOnClickListener(view -> startActivity(new Intent(this, StatisticsActivity.class)));
    }
}

