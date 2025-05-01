package com.example.lostandfound; // Replace with your actual package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ClientLogIn extends AppCompatActivity {

    Button createAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_log_in); // Matches your login XML file

        createAccountBtn = findViewById(R.id.button2); // ID for "Create Account" button

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Switch to registration screen
                Intent intent = new Intent(ClientLogIn.this, ClientRegistration.class);
                startActivity(intent);
            }
        });
    }
}