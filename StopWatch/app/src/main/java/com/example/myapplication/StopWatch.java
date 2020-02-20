package com.example.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class StopWatch extends AppCompatActivity {

    Button buttonStart, buttonStop;
    ImageView imageViewAnchor;
    Animation animationRounding;
    Chronometer mainTimer;
    Long[] savedTime = new Long[5];

    private View.OnClickListener buttonStartClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            animateClockAnchor();
            Toast.makeText(StopWatch.this, "CLICKED", Toast.LENGTH_SHORT);
            toggleButtonVisibility();
            initiateChronometer();
        }
    };

    private View.OnClickListener buttonStopClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // saves the last time:
            Long lastTimestamp = mainTimer.getBase();
            stopsChronometer();
            toggleStartButton();
            stopsAnchor();
            // Stores time into an array:
            savesTime(lastTimestamp);
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

        buttonStart.setOnClickListener(buttonStartClick);   // Start button actions
        buttonStop.setOnClickListener(buttonStopClick);   // Stop button actions

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
                .translationY(-160)
                .setDuration(300)
                .start();
        buttonStart
                .animate()
                .alpha(0)
                .setDuration(300)
                .start();
    }

    private void initiateChronometer() {
        mainTimer.setBase(SystemClock.elapsedRealtime());
        mainTimer.start();
    }

    // Stop button actions
    private void toggleStartButton() {
        // Changes stop button visibility
        buttonStop
                .animate()
                .alpha(0)
                .translationY(160)
                .setDuration(300)
                .start();
        buttonStart
                .animate()
                .alpha(1)
                .setDuration(300)
                .start();
    }

    private void stopsChronometer() {
        mainTimer.stop();
    }

    private void stopsAnchor() {
//        animationRounding.cancel();
        imageViewAnchor.setAnimation(null);
    }

    private void savesTime(Long timeToBeSaved) {
        // OBJECTIVE: Stores time into an array
        int topFive = savedTime.length; // this is always FIVE
        if (topFive < 4) {
            // when length is lower than limit
            savedTime[topFive] = timeToBeSaved;
        } else {
            // pop the first element, and change the others
            for (int i = 0; i < topFive - 2; i++) {
                savedTime[i] = savedTime[i+1];
            }
            savedTime[topFive - 1] = timeToBeSaved;
        }

        Log.d("-> savesTime() ->", "Times: " + Arrays.toString(savedTime));

    }
}
