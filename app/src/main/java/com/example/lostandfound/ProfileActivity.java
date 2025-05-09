package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    public static final String EXTRA_CLIENT_KEY = "clientKey";

    private TextView tvFullName;
    private TextView tvEmail;
    private TextView tvSchoolId;
    private TextView tvDob;
    private TextView tvPhone;
    private Button   btnLogOut;

    private String clientKey;
    private DatabaseReference clientRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // 1) Wire up views
        tvFullName     = findViewById(R.id.tvFullName);
        tvEmail        = findViewById(R.id.tvEmail);
        tvSchoolId     = findViewById(R.id.tvSchoolId);
        tvDob          = findViewById(R.id.tvDob);
        tvPhone        = findViewById(R.id.tvPhone);
        btnLogOut      = findViewById(R.id.btnLogOut);

        // 2) Read the passed-in client key
        clientKey = getIntent().getStringExtra(EXTRA_CLIENT_KEY);
        if (clientKey == null) {
            Toast.makeText(this, "No user data available", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // 3) Point at /clients/{clientKey}
        clientRef = FirebaseDatabase
                .getInstance()
                .getReference("clients")
                .child(clientKey);

        // 4) Fetch the profile fields once
        clientRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snap) {
                if (!snap.exists()) {
                    Toast.makeText(ProfileActivity.this,
                            "User not found", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }

                String fullName = snap.child("fullName").getValue(String.class);
                String email    = snap.child("email").getValue(String.class);
                String schoolId = snap.child("schoolId").getValue(String.class);
                String dob      = snap.child("dateOfBirth").getValue(String.class);
                String phone    = snap.child("phoneNumber").getValue(String.class);


                // 5) Populate the UI
                tvFullName    .setText("Name: "         + safe(fullName));
                tvEmail       .setText("Email: "        + safe(email));
                tvSchoolId    .setText("School ID: "    + safe(schoolId));
                tvDob         .setText("Date of Birth: "+ safe(dob));
                tvPhone       .setText("Phone: "        + safe(phone));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this,
                        "Error loading profile", Toast.LENGTH_SHORT).show();
            }
        });

        // 6) Log‐out → back to login
        btnLogOut.setOnClickListener(v -> {
            // (Optionally clear any stored session here)
            startActivity(new Intent(ProfileActivity.this, ClientLogIn.class));
            finish();
        });
    }

    private String safe(String s) {
        return s != null ? s : "";
    }
}


