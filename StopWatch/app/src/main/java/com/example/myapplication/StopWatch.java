package com.example.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

public class StopWatch extends AppCompatActivity {

    Button buttonStart, buttonStop;
    ImageView imageViewAnchor;
    Animation animationRounding;
    Chronometer mainTimer;
//    Long[] savedTime = new Long[5];
    Queue<String> savedTime = new LinkedList<>();
    LinearLayout savedTimeList;

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
            savesTime(lastTimestamp, savedTimeList);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        // Get elements
        buttonStart = findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonStop);
        imageViewAnchor = findViewById(R.id.imageAnchor);
        mainTimer = findViewById(R.id.mainTimer);
        savedTimeList = findViewById(R.id.savedTimesList);

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

    private void savesTime(Long timeToBeSaved, LinearLayout parentLayout) {
        // OBJECTIVE: Stores time into an QUEUE

        // Avoiding null queue:
        if (savedTime.isEmpty()) savedTime.add("");

        if (savedTime.size() < 5) {
            // when length is lower than limit
            savedTime.add(convertBaseToTime((timeToBeSaved)));
        } else {
            // pops the first, and adds the last time
            savedTime.poll();
            savedTime.add(convertBaseToTime(timeToBeSaved));
        }

        Log.d("-> savesTime() ->", "Times: " + savedTime);
        addNewTime(savedTime, parentLayout);
    }

    private String convertBaseToTime(Long baseTime) {
        // Get time from system:
        long now = SystemClock.elapsedRealtime();
        String mDefaultFormat = "%02d:%02d";
        /*
            Initiate conversion
            -
            based on https://gist.github.com/draekko/3ae830055c708ffe73a7c6a1aecf75f8
         */

        long seconds = (now - baseTime) / 1000;
        int hh = (int) (seconds / 3600);
        int mm = (int) ((seconds % 3600) / 60);
        int ss = (int) (seconds % 60);

        String toLocaltime = String.format(Locale.US, mDefaultFormat, mm, ss);

//        System.out.println(toLocaltime);    // Verify
        return toLocaltime;

    }

    private void addNewTime(Queue<String> childTimeList, LinearLayout parentLayout) {
        /*
            OBJECTIVE: Way to gets TIME and ADDS into ScrollView
         */
        int initialSize = childTimeList.size();
        Queue<String> childCopy = new LinkedList<>();
        childCopy.addAll(childTimeList);

        Log.d("-> Input Queue", "-> .copy: " + childTimeList);

        // Clears the ScrollView content:
        parentLayout.removeAllViews();

        for (int i = 0; i < initialSize; i++ ) {

            String thisPeek = childCopy.peek();

            // Creates a button & set its name to be equal to `childTime`:
            Button childButton = new Button(StopWatch.this);
            childButton.setId(i);
            childButton.setText(R.string.test_string);
            childButton.setText(childCopy.peek());
            childButton.setAllCaps(true);
            childButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
            childButton.setTextColor(getResources().getColor(R.color.colorWhite));
            childButton.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/MLight.ttf"));
            childButton.setVisibility(View.VISIBLE);
            childButton.setBackgroundResource(R.drawable.bigbuttonlightpurple);
            childButton.setLayoutParams(
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT)
            );

            Log.d("<-- Inside QUEUE -->",
                    "-> (i): " + i + ", Element: " + thisPeek +
                            ", Button Text: " + childButton.getText() +
                            ", Button TextSize: " + childButton.getTextSize()
            ); // Verify

            // Adds that button into `parentLayout`:
            parentLayout.addView(childButton);
            parentLayout.invalidate();
            parentLayout.requestLayout();

            childCopy.poll();
        }
    }
}
