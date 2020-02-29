package com.example.databasebabysteps.components;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.databasebabysteps.MainActivity;
import com.example.databasebabysteps.R;

public class AddNote extends AppCompatActivity {

    Button sendButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // Get elements
        backButton = findViewById(R.id.back_button);
        sendButton = findViewById(R.id.send_button);

        // On click BACK
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backView = new Intent(AddNote.this, MainActivity.class);
                startActivity(backView);
            }
        });

        // Save data = send to database dealing
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


}
