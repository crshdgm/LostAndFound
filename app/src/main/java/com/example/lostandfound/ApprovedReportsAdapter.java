package com.example.lostandfound;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ApprovedReportsAdapter extends RecyclerView.Adapter<ApprovedReportsAdapter.ViewHolder> {

    private List<ApprovedReport> approvedReports;

    // Constructor to initialize the list of reports
    public ApprovedReportsAdapter(List<ApprovedReport> approvedReports) {
        this.approvedReports = approvedReports;
    }

    // Create a new ViewHolder and inflate the layout for each report
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_item, parent, false); // Inflate the report_item layout
        return new ViewHolder(view);
    }

    // Bind the data from the approvedReports list to the views
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ApprovedReport report = approvedReports.get(position); // Get the report at the current position
        holder.itemName.setText(report.getItemName());
        holder.locationFound.setText(report.getLocationFound());
        holder.date.setText(report.getDate());
        holder.claimedBy.setText(report.getClaimedBy());
        holder.reportImage.setImageResource(report.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return approvedReports.size(); // Return the number of reports in the list
    }

    // ViewHolder class to hold views for each report entry
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, locationFound, date, claimedBy;
        ImageView reportImage;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.tvItemName);
            locationFound = itemView.findViewById(R.id.tvLocationFound);
            date = itemView.findViewById(R.id.tvDate);
            claimedBy = itemView.findViewById(R.id.tvClaimedBy);
            reportImage = itemView.findViewById(R.id.ivReportImage);
        }
    }
}
