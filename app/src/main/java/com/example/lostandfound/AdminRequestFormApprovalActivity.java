package com.example.lostandfound;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminRequestFormApprovalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide default ActionBar (title)
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Root ScrollView with drawable background
        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        scrollView.setBackgroundResource(R.drawable.background);

        // Container LinearLayout
        LinearLayout root = new LinearLayout(this);
        ScrollView.LayoutParams rootParams = new ScrollView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        root.setLayoutParams(rootParams);
        root.setOrientation(LinearLayout.VERTICAL);
        int pad = dp(16);
        root.setPadding(pad, pad, pad, pad);

        // Header: Lost & Found
        LinearLayout header = new LinearLayout(this);
        header.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setGravity(Gravity.CENTER_VERTICAL);

        TextView tvLost = new TextView(this);
        tvLost.setText("Lost &");
        tvLost.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        tvLost.setTypeface(Typeface.DEFAULT_BOLD);
        tvLost.setTextColor(Color.parseColor("#1A237E"));
        header.addView(tvLost);

        TextView tvFound = new TextView(this);
        LinearLayout.LayoutParams foundLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        foundLp.setMarginStart(dp(4));
        tvFound.setLayoutParams(foundLp);
        tvFound.setText("Found");
        tvFound.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        tvFound.setTypeface(Typeface.DEFAULT_BOLD);
        tvFound.setTextColor(Color.parseColor("#FFC107"));
        header.addView(tvFound);

        // Spacer
        View spacer = new View(this);
        LinearLayout.LayoutParams spacerLp = new LinearLayout.LayoutParams(0, 0, 1f);
        spacer.setLayoutParams(spacerLp);
        header.addView(spacer);

        root.addView(header);

        // Title + underline
        TextView tvTitle = new TextView(this);
        tvTitle.setText("Request Form Details");
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tvTitle.setTypeface(Typeface.DEFAULT_BOLD);
        LinearLayout.LayoutParams titleLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        titleLp.setMargins(0, dp(24), 0, 0);
        tvTitle.setLayoutParams(titleLp);
        root.addView(tvTitle);

        View underline = new View(this);
        LinearLayout.LayoutParams ulLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dp(2));
        ulLp.setMargins(0, dp(4), 0, 0);
        underline.setLayoutParams(ulLp);
        underline.setBackgroundColor(Color.BLACK);
        root.addView(underline);

        // Details
        root.addView(createDetailText("School ID: 2200000", 16));
        root.addView(createDetailText("Name: Juan Dela Cruz", 4));
        root.addView(createDetailText("Item Type: Tumbler", 4));
        root.addView(createDetailText("Date Lost: 04-05-2025", 4));
        root.addView(createDetailText("Location Lost: BYOD", 4));

        // Description
        root.addView(createDetailText("Item Description:", 8));
        TextView tvDesc = new TextView(this);
        LinearLayout.LayoutParams descLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        descLp.setMargins(0, dp(4), 0, 0);
        tvDesc.setLayoutParams(descLp);
        tvDesc.setPadding(dp(12), dp(12), dp(12), dp(12));
        tvDesc.setText("Yellow tumbler with a sticker");
        tvDesc.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        root.addView(tvDesc);

        // Status
        TextView tvStatus = new TextView(this);
        LinearLayout.LayoutParams statusLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        statusLp.setMargins(0, dp(12), 0, 0);
        tvStatus.setLayoutParams(statusLp);
        tvStatus.setText("Status: Pending");
        tvStatus.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tvStatus.setTypeface(Typeface.DEFAULT_BOLD);
        tvStatus.setTextColor(Color.parseColor("#FF9800"));
        root.addView(tvStatus);

        // Buttons
        LinearLayout btnRow = new LinearLayout(this);
        LinearLayout.LayoutParams rowLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rowLp.setMargins(0, dp(16), 0, 0);
        btnRow.setLayoutParams(rowLp);
        btnRow.setOrientation(LinearLayout.HORIZONTAL);
        btnRow.setGravity(Gravity.CENTER);
        btnRow.setWeightSum(2);

        Button btnApprove = new Button(this);
        LinearLayout.LayoutParams baLp = new LinearLayout.LayoutParams(0, dp(40), 1f);
        baLp.setMarginEnd(dp(8));
        btnApprove.setLayoutParams(baLp);
        btnApprove.setText("Approve");
        btnApprove.setAllCaps(false);
        btnApprove.setBackgroundColor(Color.parseColor("#4CAF50"));
        btnApprove.setTextColor(Color.WHITE);
        btnApprove.setOnClickListener(v -> Toast.makeText(this, "Request Approved", Toast.LENGTH_SHORT).show());
        btnRow.addView(btnApprove);

        Button btnDisapprove = new Button(this);
        LinearLayout.LayoutParams bdLp = new LinearLayout.LayoutParams(0, dp(40), 1f);
        bdLp.setMarginStart(dp(8));
        btnDisapprove.setLayoutParams(bdLp);
        btnDisapprove.setText("Disapprove");
        btnDisapprove.setAllCaps(false);
        btnDisapprove.setBackgroundColor(Color.parseColor("#F44336"));
        btnDisapprove.setTextColor(Color.WHITE);
        btnDisapprove.setOnClickListener(v -> Toast.makeText(this, "Request Disapproved", Toast.LENGTH_SHORT).show());
        btnRow.addView(btnDisapprove);

        root.addView(btnRow);

        // Submit
        Button btnSubmit = new Button(this);
        LinearLayout.LayoutParams bsLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dp(48));
        bsLp.setMargins(0, dp(24), 0, 0);
        btnSubmit.setLayoutParams(bsLp);
        btnSubmit.setText("Submit");
        btnSubmit.setAllCaps(false);
        btnSubmit.setBackgroundColor(Color.parseColor("#009688"));
        btnSubmit.setTextColor(Color.WHITE);
        btnSubmit.setOnClickListener(v -> Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show());
        root.addView(btnSubmit);

        scrollView.addView(root);
        setContentView(scrollView);
    }

    private TextView createDetailText(String text, int topDp) {
        TextView tv = new TextView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, dp(topDp), 0, 0);
        tv.setLayoutParams(lp);
        tv.setText(text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        return tv;
    }

    private int dp(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
