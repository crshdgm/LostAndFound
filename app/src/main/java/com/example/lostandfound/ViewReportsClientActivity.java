package com.example.lostandfound;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewReportsClientActivity extends AppCompatActivity {
    private LinearLayout frame;
    private DatabaseReference itemsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // **point to the client XML** instead of activity_view_reports.xml
        setContentView(R.layout.activity_view_reports_client);

        frame    = findViewById(R.id.Frame);
        itemsRef = FirebaseDatabase.getInstance()
                .getReference("items");
        loadItems();
    }

    private void loadItems() {
        itemsRef.addValueEventListener(new ValueEventListener() {
            @Override public void onDataChange(@NonNull DataSnapshot snap) {
                frame.removeAllViews();
                if (!snap.exists()) {
                    Toast.makeText(ViewReportsClientActivity.this,
                            "No items found", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (DataSnapshot child : snap.getChildren()) {
                    Item item = child.getValue(Item.class);
                    if (item != null) addItemCard(item);
                }
            }
            @Override public void onCancelled(@NonNull DatabaseError err) { /*…*/ }
        });
    }

    private void addItemCard(Item item) {
        // **inflate the client card** here
        View card = LayoutInflater.from(this)
                .inflate(R.layout.report_card_client, frame, false);

        TextView tvReq   = card.findViewById(R.id.tvRequestNumber);
        TextView tvOwn   = card.findViewById(R.id.tvOwnerName);
        TextView tvSchool= card.findViewById(R.id.tvSchoolId);
        TextView tvDate  = card.findViewById(R.id.tvDateFound);
        TextView tvDesc  = card.findViewById(R.id.tvDescription);
        Button   btnClaim= card.findViewById(R.id.btnClaim);
        Button   btnDel  = card.findViewById(R.id.btnDelete);

        tvReq .setText("Request #: " + item.getRequestNumber());
        tvOwn .setText("Owner: " + item.getName());
        tvSchool.setText("School ID: " + item.getSchoolId());
        tvDate.setText("Date Found: " + item.getDateFound());
        tvDesc.setText("Description: " + item.getDescription());

        // enable Claim only if admin approved
        // enable Claim only if admin approved
        if (item.isApproved()) {
            btnClaim.setEnabled(true);
            btnClaim.setBackgroundTintList(
                    ColorStateList.valueOf(Color.parseColor("#4CAF50"))
            );
            btnClaim.setOnClickListener(v -> {
                // …your claim logic here…
            });
        } else {
            btnClaim.setEnabled(false);
            // optionally gray it out:
            btnClaim.setBackgroundTintList(
                    ColorStateList.valueOf(Color.parseColor("#CCCCCC"))
            );
        }


        frame.addView(card);
    }
}