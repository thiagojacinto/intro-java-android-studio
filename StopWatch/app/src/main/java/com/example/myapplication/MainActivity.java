package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
