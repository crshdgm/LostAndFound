package com.example.lostandfound;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activityManageUsers extends AppCompatActivity {

    private EditText search;
    private LinearLayout UserInfo;
    private Button verifyButton;
    private Button blockButton;
    private Button historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manage_users);

        search = findViewById(R.id.search);
        UserInfo = findViewById(R.id.userInfo);
        verifyButton = findViewById(R.id.verifybtn);
        blockButton = findViewById(R.id.blockbtn);
        historyButton = findViewById(R.id.historybtn);


    }
}
