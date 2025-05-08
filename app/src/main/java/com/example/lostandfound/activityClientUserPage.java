package com.example.lostandfound;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class activityClientUserPage extends AppCompatActivity {

    private ImageView topBR;

    private ImageView bottomBR;
    private LinearLayout infoContainer;
    private View firstImage;
    private View secondImage;
    private View thirdImage;
    private View fourthImage;

    private ImageView homebtn;

    private ImageView submitBtn;
    private ImageView profilebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_user_page);

        topBR = findViewById(R.id.topBackground);
        infoContainer = findViewById(R.id.boxesContainer);
        firstImage = findViewById(R.id.image1);
        secondImage = findViewById(R.id.image2);
        thirdImage = findViewById(R.id.image3);
        fourthImage = findViewById(R.id.image4);
        homebtn = findViewById(R.id.homeIcon);
        submitBtn = findViewById(R.id.Submit);
        profilebtn = findViewById(R.id.profile);
        bottomBR = findViewById(R.id.bottomBackground);

        submitBtn.setOnClickListener(v -> {
            Intent intent = new Intent(activityClientUserPage.this, AddItemActivity.class);
            startActivity(intent);
        });

    }
}
