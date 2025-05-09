package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ManageUsersActivity extends AppCompatActivity {
    private EditText      etSearchUser;
    private LinearLayout  userContainer;
    private DatabaseReference clientsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        etSearchUser   = findViewById(R.id.etSearchUser);
        userContainer  = findViewById(R.id.userContainer);
        clientsRef     = FirebaseDatabase.getInstance()
                .getReference("clients");

        loadAllUsers();
    }

    private void loadAllUsers() {
        clientsRef.addValueEventListener(new ValueEventListener() {
            @Override public void onDataChange(@NonNull DataSnapshot snap) {
                userContainer.removeAllViews();
                if (!snap.exists()) return;              // no users

                for (DataSnapshot child : snap.getChildren()) {
                    User u = child.getValue(User.class);
                    if (u == null) continue;
                    u.setUid(child.getKey());
                    inflateUserCard(u);
                }
            }
            @Override public void onCancelled(@NonNull DatabaseError e) {
                Toast.makeText(ManageUsersActivity.this,
                        "DB error: "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void inflateUserCard(User user) {
        View card = LayoutInflater.from(this)
                .inflate(R.layout.user_card, userContainer, false);

        TextView tvName   = card.findViewById(R.id.tvUserName);
        TextView tvEmail  = card.findViewById(R.id.tvEmail);
        TextView tvDob    = card.findViewById(R.id.tvDateOfBirth);
        TextView tvPhone  = card.findViewById(R.id.tvPhoneNumber);
        TextView tvStatus = card.findViewById(R.id.tvStatus);

        Button btnBlock   = card.findViewById(R.id.btnBlock);

        tvName .setText("Name: " + user.getFullName());
        tvEmail.setText("Email: " + user.getEmail());
        tvDob  .setText("DOB: "  + user.getDateOfBirth());
        tvPhone.setText("Phone: "+ user.getPhoneNumber());
        tvStatus.setText("Status: " + (user.isBlocked() ? "Blocked" : "Active"));

        // toggle block/unblock
        btnBlock.setText(user.isBlocked() ? "Unblock" : "Block");
        btnBlock.setOnClickListener(v -> {
            boolean next = !user.isBlocked();
            clientsRef.child(user.getUid())
                    .child("blocked")
                    .setValue(next)
                    .addOnSuccessListener(a -> {
                        user.setBlocked(next);
                        tvStatus.setText("Status: " + (next ? "Blocked" : "Active"));
                        btnBlock.setText(next ? "Unblock" : "Block");
                    });
        });

        userContainer.addView(card);
    }
}
