package com.example.activityone.components;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.activityone.R;

public class EmergencyCall extends AppCompatActivity {

    Button emergencyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);

        emergencyButton = findViewById(R.id.button_emergency_call);
        // Stablish a number
        final String callNumber = "081989601783";

        emergencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numberDial = new Intent(Intent.ACTION_DIAL);

                numberDial.setData(Uri.parse("tel:" + callNumber));
                if (numberDial.resolveActivity(getPackageManager()) != null) {
                    startActivity(numberDial);
                }
            }
        });
    }
}
