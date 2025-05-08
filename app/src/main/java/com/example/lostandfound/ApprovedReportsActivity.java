//package com.example.lostandfound;
//
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ApprovedReportsActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private ApprovedReportsAdapter adapter;
//    private List<ApprovedReport> approvedReportsList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_approved_reports); // Reference to your activity layout
//
//        recyclerView = findViewById(R.id.recyclerView); // Find RecyclerView by ID
//        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set LayoutManager
//
//        // Initialize the list of reports
//        approvedReportsList = new ArrayList<>();
//        approvedReportsList.add(new ApprovedReport("USB Flash Drive", "Science Lab", "April 15, 2025", "Juan Dela Cruz", R.drawable.ic_launcher_foreground));
//        approvedReportsList.add(new ApprovedReport("Black Wallet", "Library", "April 16, 2025", "Maria Santos", R.drawable.ic_launcher_foreground));
//        // You can add more reports similarly
//
//        // Create the adapter and set it to the RecyclerView
//        adapter = new ApprovedReportsAdapter(approvedReportsList);
//        recyclerView.setAdapter(adapter);
//    }
//}
