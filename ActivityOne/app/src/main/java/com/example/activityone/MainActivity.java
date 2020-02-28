package com.example.activityone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.activityone.components.OtherActivity;

public class MainActivity extends AppCompatActivity {

    Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initiate the button
        myButton = findViewById(R.id.other_activity_button);

        // Set a onClick
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callOtherActivity = new Intent(MainActivity.this, OtherActivity.class);
                startActivity(callOtherActivity);
            }
        });
    }
}
