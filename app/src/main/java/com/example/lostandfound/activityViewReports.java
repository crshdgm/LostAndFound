package com.example.lostandfound;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class activityViewReports extends AppCompatActivity {

    private LinearLayout frame;
    private TextView title;
    private ImageView itemimage;

    private TextView description;

    private Button approveButton;

    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_reports);

        frame = findViewById(R.id.Frame);
        title = findViewById(R.id.Title);
        itemimage = findViewById(R.id.itemImage);
        description = findViewById(R.id.description);
        approveButton = findViewById(R.id.approvebtn);
        deleteButton = findViewById(R.id.deletebtn);

    }
}
