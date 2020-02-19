package com.example.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Variables
    TextView textViewSplash, textViewSubSplash;
    Button buttonGetStarted;

    Animation animationATG, animationBOne, animationBTwo;
    ImageView imageViewSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get items from XML file:
        textViewSplash = findViewById(R.id.textViewSplash);
        textViewSubSplash = findViewById(R.id.textViewSubSplash);
        buttonGetStarted = findViewById(R.id.buttonGetStarted);

        imageViewSplash = findViewById(R.id.imageViewSplash);
        // Load animation
        animationATG = AnimationUtils.loadAnimation(this, R.anim.atg);
        animationBOne = AnimationUtils.loadAnimation(this, R.anim.anim_one);
        animationBTwo = AnimationUtils.loadAnimation(this, R.anim.anim_two);

        // Using animation:
        imageViewSplash.startAnimation(animationATG);
        textViewSplash.startAnimation(animationBOne);
        textViewSubSplash.startAnimation(animationBTwo);
        buttonGetStarted.startAnimation(animationBTwo);

        // Set a click listener on `get_started` button:
        buttonGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On click, it will lead to the activity STOPWATCH:
                Intent goToStopWatch = new Intent(MainActivity.this, StopWatch.class);
//                goToStopWatch.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(goToStopWatch);

            }
        });

        // Importing added fonts:
        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");
        Typeface MRegular = Typeface.createFromAsset(getAssets(), "fonts/MRegular.ttf");

        // Use customized fonts with:
        textViewSplash.setTypeface(MRegular);
        textViewSubSplash.setTypeface(MLight);
        buttonGetStarted.setTypeface(MMedium);

    }
}
