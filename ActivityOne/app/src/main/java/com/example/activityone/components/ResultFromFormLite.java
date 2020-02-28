package com.example.activityone.components;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.activityone.R;

public class ResultFromFormLite extends AppCompatActivity {

    Button backButton;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_from_form_lite);

        // Get elements
        backButton = findViewById(R.id.back_button);
        resultText = findViewById(R.id.text_view_result);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToFormActivity = new Intent(ResultFromFormLite.this, Form.class);
                startActivity(backToFormActivity);
            }
        });

        Bundle external = getIntent().getExtras();
        String textCaught = external.getString("name");

        resultText.setText(textCaught);
    }
}
