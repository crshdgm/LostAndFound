package com.example.lostandfound;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewReportsActivity extends AppCompatActivity {

    LinearLayout reportsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reports);

        reportsContainer = findViewById(R.id.reportsContainer);

        // Add a sample report entry
        addReportEntry("Black wallet found near library", R.drawable.ic_launcher_foreground);
    }

    private void addReportEntry(String description, int imageResId) {
        // Inflate the report entry layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View reportEntryView = inflater.inflate(R.layout.item_report_entry, reportsContainer, false);

        // Set the data (Description & Image)
        TextView descriptionText = reportEntryView.findViewById(R.id.reportDescription);
        descriptionText.setText(description);

        ImageView reportImage = reportEntryView.findViewById(R.id.reportImage);
        reportImage.setImageResource(imageResId);

        // Get the buttons
        Button btnApprove = reportEntryView.findViewById(R.id.btnApprove);
        Button btnDelete = reportEntryView.findViewById(R.id.btnDelete);

        // Set button actions
        btnApprove.setOnClickListener(v -> {
            // Handle approve action
        });

        btnDelete.setOnClickListener(v -> {
            // Handle delete action
        });

        // Add the view to the container
        reportsContainer.addView(reportEntryView);
    }
}
