package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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

public class ViewReportsActivity extends AppCompatActivity {

    private LinearLayout frame;
    private DatabaseReference itemsRef;
    private DatabaseReference claimedRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reports);

        frame       = findViewById(R.id.Frame);
        itemsRef    = FirebaseDatabase.getInstance()
                .getReference("items");
        claimedRef  = FirebaseDatabase.getInstance()
                .getReference("claimedItems");
        loadItems();
    }

    private void loadItems() {
        itemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                frame.removeAllViews();
                if (!snapshot.exists()) {
                    Toast.makeText(ViewReportsActivity.this,
                            "No items found", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (DataSnapshot child : snapshot.getChildren()) {
                    Item item = child.getValue(Item.class);
                    if (item != null) {
                        addItemCard(item);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewReportsActivity.this,
                        "Database error: " + error.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addItemCard(Item item) {
        // 1) Inflate your card layout
        View card = LayoutInflater.from(this)
                .inflate(R.layout.report_card, frame, false);

        // 2) Find your views
        TextView tvRequest   = card.findViewById(R.id.tvRequestNumber);
        TextView tvOwner     = card.findViewById(R.id.tvOwnerName);
        TextView tvSchoolId  = card.findViewById(R.id.tvSchoolId);
        TextView tvDateFound = card.findViewById(R.id.tvDateFound);
        TextView tvDesc      = card.findViewById(R.id.description);
        Button   btnApprove  = card.findViewById(R.id.btnApprove);
        Button   btnClaim    = card.findViewById(R.id.btnClaim);
        Button   btnDelete   = card.findViewById(R.id.btnDelete);

        // 3) Populate text
        tvRequest  .setText("Request #: "  + item.getRequestNumber());
        tvOwner    .setText("Owner: "      + item.getName());
        tvSchoolId .setText("School ID: "  + item.getSchoolId());
        tvDateFound.setText("Date Found: " + item.getDateFound());
        tvDesc     .setText("Description: "+ item.getDescription());

        // 4) APPROVE → simply flag approved=true
        btnApprove.setOnClickListener(v -> {
            itemsRef.child(item.getRequestNumber())
                    .child("approved")
                    .setValue(true)
                    .addOnSuccessListener(a -> {
                        Toast.makeText(this, "Report approved", Toast.LENGTH_SHORT).show();
                        frame.removeView(card);
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(this, "Approve failed: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show()
                    );
        });

        // 5) CLAIM → move into claimedItems node, then remove from items
        btnClaim.setOnClickListener(v -> {
            claimedRef.child(item.getRequestNumber())
                    .setValue(item)
                    .addOnSuccessListener(a -> {
                        itemsRef.child(item.getRequestNumber()).removeValue();
                        Toast.makeText(this, "Item claimed!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, ClaimedItemsActivity.class));
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(this, "Claim failed: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show()
                    );
        });

        // 6) DELETE → remove outright
        btnDelete.setOnClickListener(v -> {
            itemsRef.child(item.getRequestNumber())
                    .removeValue()
                    .addOnSuccessListener(a ->
                            Toast.makeText(this, "Report deleted", Toast.LENGTH_SHORT).show()
                    )
                    .addOnFailureListener(e ->
                            Toast.makeText(this, "Delete failed: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show()
                    );
        });

        // 7) Add it into your scroll-container
        frame.addView(card);
    }
}
