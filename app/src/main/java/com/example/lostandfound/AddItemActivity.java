package com.example.lostandfound;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    EditText etItemName, etDescription, etDateFound, etOwnerName;
    Button btnUploadPhoto, btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // Initialize views
        etItemName = findViewById(R.id.etItemName);
        etDescription = findViewById(R.id.etDescription);
        etDateFound = findViewById(R.id.etDateFound);
        etOwnerName = findViewById(R.id.etOwnerName);
        btnUploadPhoto = findViewById(R.id.btnUploadPhoto);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement image picker logic
                Toast.makeText(AddItemActivity.this, "Upload Photo Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = etItemName.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                String dateFound = etDateFound.getText().toString().trim();
                String ownerName = etOwnerName.getText().toString().trim();

                if (itemName.isEmpty() || description.isEmpty() || dateFound.isEmpty() || ownerName.isEmpty()) {
                    Toast.makeText(AddItemActivity.this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    // TODO: Save data to database or send to server
                    Toast.makeText(AddItemActivity.this, "Item Submitted!", Toast.LENGTH_SHORT).show();
                    // Optionally clear fields
                    etItemName.setText("");
                    etDescription.setText("");
                    etDateFound.setText("");
                    etOwnerName.setText("");
                }
            }
        });
    }
}
