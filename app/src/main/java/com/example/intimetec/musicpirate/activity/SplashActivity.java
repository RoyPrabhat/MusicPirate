package com.example.intimetec.musicpirate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.intimetec.musicpirate.MusicPirateActivity;
import com.example.intimetec.musicpirate.R;

/**
 * Created by ROY on 7/31/2016.
 */
public class SplashActivity extends MusicPirateActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
            }
        }, SPLASH_TIME_OUT);
    }
}
