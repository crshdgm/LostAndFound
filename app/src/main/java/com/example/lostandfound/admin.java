package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class admin extends AppCompatActivity {
    private static final String TAG = "Admin DashBoard";

    private Button addItemField;
    private Button viewReportField;
    private Button viewApprovedReports;
    private Button manageUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1) Find your views
        addItemField = findViewById(R.id.btnAddItem);
        viewReportField = findViewById(R.id.btnViewReports);
        viewApprovedReports = findViewById(R.id.btnApprovedReports);
        manageUser = findViewById(R.id.btnManageUsers);

        addItemField.setOnClickListener(v -> {
            Intent intent = new Intent(admin.this, AddItemActivity.class);
            startActivity(intent);
        });

        viewReportField.setOnClickListener(v -> {
            Intent intent = new Intent(admin.this, ActivityViewReports.class);
            startActivity(intent);
        });

        viewApprovedReports.setOnClickListener(v -> {
            Intent intent = new Intent(admin.this, activityApprovedReports.class);
            startActivity(intent);
        });

        manageUser.setOnClickListener(v -> {
            Intent intent = new Intent(admin.this, activityManageUsers.class);
            startActivity(intent);
        });



        }
}