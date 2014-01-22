package com.radiostar.android.view;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.content.Intent;

import com.radiostar.android.R;

public class StartScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startscreen);
        //PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(StartScreen.this, TtsView.class);
                StartScreen.this.startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
