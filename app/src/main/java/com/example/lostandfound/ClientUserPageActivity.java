package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;import com.google.firebase.database.ValueEventListener;

public class ClientUserPageActivity extends AppCompatActivity {
    public static final String EXTRA_CLIENT_KEY = "clientKey";

    private ImageView background;
    private ImageView logo;
    private TextView  tvWelcome;
    private Button    btnMyReports;
    private Button    btnSubmitReport;
    private Button    btnProfile;

    private String clientKey;
    private DatabaseReference clientRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_user_page);

        // 1) Grab the clientKey from Intent
        clientKey = getIntent().getStringExtra(EXTRA_CLIENT_KEY);
        if (clientKey == null) {
            Toast.makeText(this, "No user key passed in!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // 2) Wire up views
        background       = findViewById(R.id.imageViewBackground);
        logo             = findViewById(R.id.imageView8);
        tvWelcome        = findViewById(R.id.textView5);
        btnMyReports     = findViewById(R.id.btnMyReports);
        btnSubmitReport  = findViewById(R.id.btnSubmitReport);
        btnProfile       = findViewById(R.id.btnProfile);

        // 3) Point at /clients/{clientKey}
        clientRef = FirebaseDatabase
                .getInstance()
                .getReference("clients")
                .child(clientKey);

        // 4) Load and display the user’s full name
        clientRef.child("fullName")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override public void onDataChange(@NonNull DataSnapshot snap) {
                        String name = snap.getValue(String.class);
                        if (name != null) {
                            tvWelcome.setText("Welcome back, " + name + "!");
                        }
                    }
                    @Override public void onCancelled(@NonNull DatabaseError err) {
                        Toast.makeText(ClientUserPageActivity.this,
                                "Could not load your name: " + err.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        // 5) Button handlers
        btnMyReports.setOnClickListener(v -> {
            Intent i = new Intent(this, ViewReportsClientActivity.class);
            i.putExtra(EXTRA_CLIENT_KEY, clientKey);
            startActivity(i);
        });

        btnSubmitReport.setOnClickListener(v -> {
            Intent i = new Intent(this, AddItemActivity.class);
            i.putExtra(EXTRA_CLIENT_KEY, clientKey);
            startActivity(i);
        });

        btnProfile.setOnClickListener(v -> {
            Intent i = new Intent(this, ProfileActivity.class);
            // pass along the client’s key if you have one:
            String clientKey = getIntent().getStringExtra("clientKey");
            i.putExtra(ProfileActivity.EXTRA_CLIENT_KEY, clientKey);
            startActivity(i);
        });
    }
}
