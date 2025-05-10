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
        private DatabaseReference claimedRef;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_reports_client);

            frame = findViewById(R.id.Frame);

            // point at /items
            itemsRef = FirebaseDatabase
                    .getInstance()
                    .getReference("items");

            // point at /claimedItems
            claimedRef = FirebaseDatabase
                    .getInstance()
                    .getReference("claimedItems");

            loadItems();
        }

        private void loadItems() {
            itemsRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snap) {
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

                @Override
                public void onCancelled(@NonNull DatabaseError err) { /*…*/ }
            });
        }

        private void addItemCard(Item item) {
            View card = LayoutInflater.from(this)
                    .inflate(R.layout.report_card_client, frame, false);

            TextView tvReq    = card.findViewById(R.id.tvRequestNumber);
            TextView tvOwn    = card.findViewById(R.id.tvOwnerName);
            TextView tvSchool = card.findViewById(R.id.tvSchoolId);
            TextView tvDate   = card.findViewById(R.id.tvDateFound);
            TextView tvDesc   = card.findViewById(R.id.tvDescription);

            Button btnClaim   = card.findViewById(R.id.btnClaim);
            // we removed the delete button from the client UI
            // Button btnDel   = card.findViewById(R.id.btnDelete);

            // populate …
            tvReq   .setText("Request #: "   + item.getRequestNumber());
            tvOwn   .setText("Owner: "        + item.getName());
            tvSchool.setText("School ID: "    + item.getSchoolId());
            tvDate  .setText("Date Found: "   + item.getDateFound());
            tvDesc  .setText("Description: "  + item.getDescription());

            // start disabled
            btnClaim.setEnabled(false);
            // if already approved by admin, enable immediately:
            if (item.isApproved()) {
                btnClaim.setEnabled(true);
                btnClaim.setBackgroundTintList(
                        ColorStateList.valueOf(Color.parseColor("#4CAF50"))
                );
            }

            btnClaim.setOnClickListener(v -> {
                // immediately turn green + disable so they can't double-tap
                btnClaim.setEnabled(false);
                btnClaim.setBackgroundTintList(
                        ColorStateList.valueOf(Color.parseColor("#4CAF50"))
                );

                // 1) copy into /claimedItems
                claimedRef.child(item.getRequestNumber())
                        .setValue(item)
                        .addOnSuccessListener(a -> {
                            // 2) remove from /items so onDataChange() will drop it from this list
                            itemsRef.child(item.getRequestNumber()).removeValue();
                            Toast.makeText(this, "Report claimed!", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e ->
                                Toast.makeText(this, "Claim failed: "+e.getMessage(),
                                        Toast.LENGTH_LONG).show()
                        );
            });

            frame.addView(card);
        }
    }
