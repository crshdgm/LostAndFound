package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClientRegistration extends AppCompatActivity {
    private EditText fullNameEt, dobEt, phoneEt, emailEt, passwordEt;
    private Button   registerBtn;
    private DatabaseReference clientsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_registration);

        // Initialize Firebase SDK
        FirebaseApp.initializeApp(this);

        // 1) find views
        fullNameEt  = findViewById(R.id.FullName);
        dobEt       = findViewById(R.id.DateOfBirth);
        phoneEt     = findViewById(R.id.PhoneNumber);
        emailEt     = findViewById(R.id.SchoolEmail);
        passwordEt  = findViewById(R.id.Password);
        registerBtn = findViewById(R.id.CreateAccount);

        // 2) database reference
        clientsRef = FirebaseDatabase.getInstance()
                .getReference("clients");

        // 3) set listener to call helper once
        registerBtn.setOnClickListener(v -> {
            Log.d("ClientReg", "CreateAccount clicked");
            registerNewClient();
        });
    }

    private void registerNewClient() {
        Log.d("ClientReg", "registerNewClient() start");
        String fullName = fullNameEt.getText().toString().trim();
        String dob      = dobEt.getText().toString().trim();
        String phone    = phoneEt.getText().toString().trim();
        String email    = emailEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();

        if (TextUtils.isEmpty(fullName)
                || TextUtils.isEmpty(dob)
                || TextUtils.isEmpty(phone)
                || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Client client = new Client(fullName, dob, phone, email, password);
        String key = clientsRef.push().getKey();
        Log.d("ClientReg", "Generated key = " + key);

        // 4) push only once here
        clientsRef.child(key)
                .setValue(client)
                .addOnSuccessListener(aVoid -> {
                    Log.d("ClientReg", "write succeeded");
                    Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
                    // redirect to login
                    startActivity(new Intent(this, ClientLogIn.class));
                    finish();
                })
                .addOnFailureListener(e -> {
                    Log.e("ClientReg", "write failed", e);
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }
}
