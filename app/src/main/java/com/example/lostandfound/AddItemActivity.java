package com.example.lostandfound;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddItemActivity extends AppCompatActivity {
    private static final String TAG = "AddItemActivity";
    private static final int PICK_IMAGE_REQUEST = 1001;

    private EditText etItemName, etDescription, etDateFound, etOwnerName;
    private Button btnUploadPhoto, btnSubmit;
    private Uri selectedImageUri;
    private StorageReference imagesRef;
    private DatabaseReference itemsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // Initialize Firebase SDK
        FirebaseApp.initializeApp(this);

        // 1) findViewById for each view
        etItemName = findViewById(R.id.etItemName);
        etDescription = findViewById(R.id.etDescription);
        etDateFound = findViewById(R.id.etDateFound);
        etOwnerName = findViewById(R.id.etOwnerName);
        btnUploadPhoto = findViewById(R.id.btnUploadPhoto);
        btnSubmit = findViewById(R.id.btnSubmit);

        // 2) get Firebase references
        imagesRef = FirebaseStorage.getInstance().getReference("item_photos");
        itemsRef = FirebaseDatabase.getInstance().getReference("items");

        // 3) wire up buttons
        btnUploadPhoto.setOnClickListener(v -> openImagePicker());
        btnSubmit.setOnClickListener(v -> registerNewItem());
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void registerNewItem() {
        String name = etItemName.getText().toString().trim();
        String desc = etDescription.getText().toString().trim();
        String dateFound = etDateFound.getText().toString().trim();
        String owner = etOwnerName.getText().toString().trim();

        // simple validation
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(desc)
                || TextUtils.isEmpty(dateFound) || TextUtils.isEmpty(owner)) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // if no image, push directly
        if (selectedImageUri == null) {
            pushItemToDatabase(name, desc, dateFound, owner, null);
        } else {
            String filename = System.currentTimeMillis() + ".jpg";
            imagesRef.child(filename)
                    .putFile(selectedImageUri)
                    .addOnSuccessListener(taskSnapshot ->
                            imagesRef.child(filename)
                                    .getDownloadUrl()
                                    .addOnSuccessListener(uri -> pushItemToDatabase(
                                            name, desc, dateFound, owner, uri.toString()
                                    ))
                    )
                    .addOnFailureListener(e -> Toast.makeText(
                            this,
                            "Upload failed: " + e.getMessage(),
                            Toast.LENGTH_LONG
                    ).show());
        }
    }

    private void pushItemToDatabase(String name,
                                    String desc,
                                    String dateFound,
                                    String owner,
                                    String imageUrl) {
        String key = itemsRef.push().getKey();
        if (key == null) {
            Toast.makeText(this,
                    "Error generating key, try again",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Item item = new Item(name, desc, dateFound, owner, imageUrl);
        itemsRef.child(key)
                .setValue(item)
                .addOnSuccessListener(aVoid -> {
                    Log.d(TAG, "Item added: " + key);
                    Toast.makeText(this,
                            "Item successfully added",
                            Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this,
                            "DB Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                });
    }
}