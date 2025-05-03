package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClientLogIn extends AppCompatActivity {

    private static final String TAG = "ClientLogIn";

    private EditText emailField;
    private EditText passwordField;
    private Button   loginBtn;
    private Button   createAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_log_in);

        // 1) Find your views
        emailField        = findViewById(R.id.UserEmail);
        passwordField     = findViewById(R.id.UserPassword);
        loginBtn          = findViewById(R.id.LogInButton);
        createAccountBtn  = findViewById(R.id.CreateAccountButton);

        // 2) Wire up the Create Account button
        createAccountBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ClientLogIn.this, ClientRegistration.class);
            startActivity(intent);
        });

        // 3) Wire up the Log In button
        loginBtn.setOnClickListener(v -> {
            String email    = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this,
                        "Please enter both email and password",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            performLogin(email, password);
        });
    }

    private void performLogin(String email, String password) {
        // 4) Get a reference to /clients
        DatabaseReference clientsRef = FirebaseDatabase
                .getInstance()
                .getReference("clients");

        // 5) Query by the "email" child
        clientsRef
                .orderByChild("email")
                .equalTo(email)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (!snapshot.exists()) {
                            Toast.makeText(ClientLogIn.this,
                                    "No account found for that email",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        // Should be exactly one match if you enforce unique emails
                        for (DataSnapshot userSnap : snapshot.getChildren()) {
                            String dbPassword = userSnap
                                    .child("password")
                                    .getValue(String.class);

                            if (password.equals(dbPassword)) {
                                // Login success
                                String clientKey = userSnap.getKey();
                                Toast.makeText(ClientLogIn.this,
                                        "Welcome back!",
                                        Toast.LENGTH_SHORT).show();
                                goToUserPage(clientKey);
                            } else {
                                //Wrong password
                                Toast.makeText(ClientLogIn.this,
                                        "Incorrect password",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.e(TAG, "Login query failed", error.toException());
                        Toast.makeText(ClientLogIn.this,
                                "Database error, try again",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void goToUserPage(String clientKey) {
        Intent i = new Intent(this, ClientUserPageActivity.class);
        i.putExtra("clientKey", clientKey);
        startActivity(i);
        finish();
    }
}
