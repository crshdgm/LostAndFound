package com.example.lostandfound;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DataSnapshot;


public class AddItemActivity extends AppCompatActivity {
    private EditText etName, etOwner, etSchoolId, etDateFound, etDescription;
    private Button   btnSubmit;
    private DatabaseReference itemsRef, counterRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // 1) wire up your EditTexts + button
        etName        = findViewById(R.id.etItemName);
        etOwner       = findViewById(R.id.etOwnerName);
        etSchoolId    = findViewById(R.id.etSchoolId);
        etDateFound   = findViewById(R.id.etDateFound);
        etDescription = findViewById(R.id.etDescription);
        btnSubmit     = findViewById(R.id.btnSubmit);

        // 2) get your RealtimeDB refs
        FirebaseApp.initializeApp(this);
        itemsRef   = FirebaseDatabase.getInstance().getReference("items");
        counterRef = FirebaseDatabase.getInstance().getReference("itemRequestCounter");

        // 3) submit
        btnSubmit.setOnClickListener(v -> registerNewItem());
    }

    private void registerNewItem() {
        String name      = etName      .getText().toString().trim();
        String owner     = etOwner     .getText().toString().trim();
        String schoolId  = etSchoolId  .getText().toString().trim();
        String dateFound = etDateFound .getText().toString().trim();
        String desc      = etDescription.getText().toString().trim();

        if (name.isEmpty() || owner.isEmpty() || schoolId.isEmpty()
                || dateFound.isEmpty() || desc.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // bump & fetch new Request #
        counterRef.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData current) {
                Long count = current.getValue(Long.class);
                if (count == null) count = 0L;
                current.setValue(count + 1);
                return Transaction.success(current);
            }
            @Override
            public void onComplete(DatabaseError err, boolean committed, DataSnapshot snap) {
                if (err != null || !committed) {
                    Toast.makeText(AddItemActivity.this,
                            "Could not reserve request number", Toast.LENGTH_SHORT).show();
                    return;
                }
                long reqNum = snap.getValue(Long.class);
                pushItem(reqNum, name, owner, schoolId, dateFound, desc);
            }
        });
    }

    private void pushItem(long reqNum,
                          String name,
                          String owner,
                          String schoolId,
                          String dateFound,
                          String desc) {
        String key = String.valueOf(reqNum);
        Item item = new Item(key, name, schoolId, dateFound, desc);
        itemsRef.child(key)
                .setValue(item)
                .addOnSuccessListener(a -> {
                    Toast.makeText(this,
                            "Added (Request # " + reqNum + ")", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(this,
                        "DB Error: " + e.getMessage(), Toast.LENGTH_LONG).show());
    }
}
