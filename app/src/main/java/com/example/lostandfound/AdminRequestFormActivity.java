package com.example.lostandfound;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminRequestFormActivity extends AppCompatActivity {

    private EditText etSearch;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_request_form); // XML layout name

        etSearch = findViewById(R.id.etSearch);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Search filter listener (optional: implement list filtering logic here)
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Optional
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // You can implement dynamic filtering of request views here
                // Example: filterCards(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Optional
            }
        });

        // Submit button listener
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminRequestFormActivity.this, "Submitted!", Toast.LENGTH_SHORT).show();

                // You can later add logic here to update request statuses, etc.
            }
        });
    }
}
