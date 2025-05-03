package com.example.lostandfound;

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

public class ClientRegistration extends AppCompatActivity {
    private static final String TAG = "RegActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_registration);

        EditText nameField  = findViewById(R.id.FullName);
        EditText dobField   = findViewById(R.id.DateOfBirth);
        EditText phoneField = findViewById(R.id.PhoneNumber);
        EditText emailField = findViewById(R.id.SchoolEmail);
        EditText passwordField = findViewById(R.id.Password);
        Button  signInBtn   = findViewById(R.id.SignInButton);


        DatabaseReference clientsRef =
                FirebaseDatabase.getInstance()
                        .getReference("clients");

        FirebaseDatabase.getInstance()
                .getReference(".info/connected")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snap) {
                        Boolean connected = snap.getValue(Boolean.class);
                        Log.i(TAG, "Connected to Firebase? " + connected);
                        Toast.makeText(ClientRegistration.this,
                                connected ? "Online" : "Offline",
                                Toast.LENGTH_SHORT).show();
                    }
                    @Override public void onCancelled(DatabaseError e) { }
                });

        signInBtn.setOnClickListener(v -> {
            String name  = nameField.getText().toString().trim();
            String dob   = dobField.getText().toString().trim();
            String phone = phoneField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String email = emailField.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                Log.e(TAG, "Missing name or email or phone number or password");
                Toast.makeText(this, "Name, email, phone number and password are required", Toast.LENGTH_SHORT).show();
                return;
            }

            String key = clientsRef.push().getKey();
            if (key == null) {
                Log.e(TAG, "Could not get push key");
                Toast.makeText(this, "Unable to generate key", Toast.LENGTH_SHORT).show();
                return;
            }

            clientsRef.child(key).child("name").setValue(name);
            clientsRef.child(key).child("dob" ).setValue(dob);
            clientsRef.child(key).child("phonenumber").setValue(phone);
            clientsRef.child(key).child("password").setValue(password);
            clientsRef.child(key).child("email").setValue(email)
                    .addOnSuccessListener(_a -> {
                        Log.i(TAG, "Wrote client " + key);
                        Toast.makeText(this, "Saved OK!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(err -> {
                        Log.e(TAG, "Write failed", err);
                        Toast.makeText(this, "Save failed, try again", Toast.LENGTH_SHORT).show();
                    });
        });
    }
}
