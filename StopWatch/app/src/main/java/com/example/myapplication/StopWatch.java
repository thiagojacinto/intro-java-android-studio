package com.example.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class StopWatch extends AppCompatActivity {

    Button buttonStart, buttonStop;
    ImageView imageViewAnchor;
    Animation animationRounding;
    Chronometer mainTimer;

    private View.OnClickListener buttonStartClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            animateClockAnchor();

            toggleButtonVisibility();

            initiateChronometer();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        // Get elements
        buttonStart = findViewById(R.id.buttonStart);
        buttonStop = findViewById(R.id.buttonStop);
        imageViewAnchor = findViewById(R.id.imageAnchor);
        mainTimer = findViewById(R.id.mainTimer);

        // Load the animation
        animationRounding = AnimationUtils.loadAnimation(this, R.anim.anim_rounding);

        // Optional animation of STOP button:
        buttonStop.setAlpha(0);

        // Load external fonts from asset:
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");
        // Apply that font into button
        buttonStart.setTypeface(MMedium);
        buttonStop.setTypeface(MMedium);

        buttonStart.setOnClickListener(buttonStartClick);
    }

    private void animateClockAnchor() {
        // Pass the animation into Anchor
        imageViewAnchor.startAnimation(animationRounding);

    }

    private void toggleButtonVisibility() {
        // Changes stop button visibility
        buttonStop
                .animate()
                .alpha(1)
                .translationY(-80)
                .setDuration(300)
                .start();
        buttonStart
                .animate()
                .alpha(10)
                .setDuration(300)
                .start();

    }

    private void initiateChronometer() {
        mainTimer.setBase(SystemClock.elapsedRealtime());
        mainTimer.start();
    }
}
