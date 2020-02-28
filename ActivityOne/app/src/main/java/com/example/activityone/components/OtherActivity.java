package com.example.activityone.components;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.activityone.MainActivity;
import com.example.activityone.R;

public class OtherActivity extends AppCompatActivity {

    Button backButton, formButton, cameraButton, emergencyCallButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        // Get elements
        backButton = findViewById(R.id.back_button);
        formButton = findViewById(R.id.button_one);
        cameraButton = findViewById(R.id.button_two);
        emergencyCallButton = findViewById(R.id.button_three);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMainActivity = new Intent(OtherActivity.this, MainActivity.class);
                startActivity(backToMainActivity);
            }
        });


    }
}
