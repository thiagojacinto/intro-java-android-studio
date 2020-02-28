package com.example.activityone.components;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.activityone.R;

public class Form extends AppCompatActivity {

    Button backButton, sendButton;
    EditText editableData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_lite);

        // Get elements
        backButton = findViewById(R.id.back_button);
        sendButton = findViewById(R.id.send_data_button);
        editableData = findViewById(R.id.edited_text);

        // Back button handler
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Moving to `OtherActivity`
                Intent backToOtherActivity = new Intent(Form.this, OtherActivity.class);
                startActivity(backToOtherActivity);
            }
        });

        // Send data button handler
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataTyped = editableData.getEditableText().toString();

                Intent sendData = new Intent(Form.this, ResultFromFormLite.class);
                sendData.putExtra("name", dataTyped);
                startActivity(sendData);
            }
        });

    }

}
