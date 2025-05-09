package com.example.lostandfound;

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

public class ClaimedItemsActivity extends AppCompatActivity {
    // 1) Declare the container
    private LinearLayout frame;
    private DatabaseReference claimedRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 2) Inflate your layout
        setContentView(R.layout.activity_claimed_items);

        // 3) Wire up your vertical container RIGHT HERE
        //    Make sure your activity_claimed_items.xml has a LinearLayout
        //    (inside the ScrollView) with android:id="@+id/Frame"
        frame = findViewById(R.id.Frame);

        // 4) Get a reference to /claimedItems in your RTDB
        claimedRef = FirebaseDatabase
                .getInstance()
                .getReference("claimedItems");

        // 5) Begin listening
        loadClaimedItems();
    }

    private void loadClaimedItems() {
        claimedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snap) {
                frame.removeAllViews();
                if (!snap.exists()) {
                    Toast.makeText(ClaimedItemsActivity.this,
                            "No claimed items found", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (DataSnapshot child : snap.getChildren()) {
                    Item item = child.getValue(Item.class);
                    if (item != null) addClaimedCard(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError err) {
                Toast.makeText(ClaimedItemsActivity.this,
                        "DB error: " + err.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addClaimedCard(Item item) {
        // inflate your report_card.xml
        View card = LayoutInflater.from(this)
                .inflate(R.layout.report_card, frame, false);

        // find & populate all fields
        TextView tvRequest = card.findViewById(R.id.tvRequestNumber);
        TextView tvName = card.findViewById(R.id.tvOwnerName);
        TextView tvSchoolId = card.findViewById(R.id.tvSchoolId);
        TextView tvDateFound = card.findViewById(R.id.tvDateFound);
        TextView tvDesc = card.findViewById(R.id.description);

        tvRequest.setText("Request #: " + item.getRequestNumber());
        tvName.setText("Owner: " + item.getName());
        tvSchoolId.setText("School ID: " + item.getSchoolId());
        tvDateFound.setText("Date Found: " + item.getDateFound());
        tvDesc.setText("Description: " + item.getDescription());

        // hide the Approve button
        Button btnApprove = card.findViewById(R.id.btnApprove);
        if (btnApprove != null) {
            btnApprove.setVisibility(View.GONE);
        }

        // hide the Claim button
        Button btnClaim = card.findViewById(R.id.btnClaim);
        if (btnClaim != null) {
            btnClaim.setVisibility(View.GONE);
        }

        // let Delete remove it from /claimedItems
        Button btnDelete = card.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(v ->
                claimedRef.child(item.getRequestNumber())
                        .removeValue()
                        .addOnSuccessListener(a ->
                                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                        )
                        .addOnFailureListener(e ->
                                Toast.makeText(this, "Delete failed: " + e.getMessage(),
                                        Toast.LENGTH_LONG).show()
                        )
        );

        frame.addView(card);
    }
}
