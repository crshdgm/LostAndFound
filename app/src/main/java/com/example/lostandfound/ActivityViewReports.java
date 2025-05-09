package com.example.lostandfound;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityViewReports extends AppCompatActivity {

    private LinearLayout frame;
    private DatabaseReference itemsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reports);

        frame    = findViewById(R.id.Frame);
        itemsRef = FirebaseDatabase.getInstance().getReference("items");
        loadItems();
    }

    private void loadItems() {
        itemsRef.addValueEventListener(new ValueEventListener() {
            @Override public void onDataChange(@NonNull DataSnapshot snap) {
                frame.removeAllViews();
                if (!snap.exists()) {
                    Toast.makeText(ActivityViewReports.this,
                            "No items found", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (DataSnapshot child : snap.getChildren()) {
                    Item item = child.getValue(Item.class);
                    if (item != null) addItemCard(item);
                }
            }
            @Override public void onCancelled(@NonNull DatabaseError err) {
                Toast.makeText(ActivityViewReports.this,
                        "DB error: " + err.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addItemCard(Item item) {
        View card = LayoutInflater.from(this)
                .inflate(R.layout.report_card, frame, false);

        TextView tvOwnerName = card.findViewById(R.id.tvOwnerName);
        TextView tvSchoolId  = card.findViewById(R.id.tvSchoolId);
        TextView tvDateFound = card.findViewById(R.id.tvDateFound);
        TextView tvDesc      = card.findViewById(R.id.description);

        tvOwnerName.setText("Owner: "     + item.getOwner());
        tvSchoolId .setText("School ID: " + item.getSchoolId());
        tvDateFound.setText("Date Found: "+ item.getDateFound());
        tvDesc     .setText("Description: " + item.getDescription());

        frame.addView(card);
    }
}

