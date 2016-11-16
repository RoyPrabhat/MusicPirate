package com.example.intimetec.musicpirate.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.intimetec.musicpirate.MusicPirateActivity;
import com.example.intimetec.musicpirate.R;
import com.example.intimetec.musicpirate.fragment.MainFragment;
import com.example.intimetec.musicpirate.util.AlertDialogueUtil;
import com.example.intimetec.musicpirate.util.SharedPreferenceManager;

public class MainActivity extends MusicPirateActivity {

    private static final String TAG = "MusicPirateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setUpToolbar(toolbar, getString(R.string.app_name));
        if (!SharedPreferenceManager.haveShownWelcomeMessage(getApplicationContext())) {
            AlertDialogueUtil.showWelcomeMessage(this);
            SharedPreferenceManager.saveWelcomeMessageShown(getApplicationContext());
        }

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_activity_option_container);

        if (fragment == null) {
            fragment = new MainFragment();
            fm.beginTransaction()
                    .add(R.id.main_activity_option_container, fragment)
                    .commit();
            Log.i(TAG, "Settings fragment added successfully");
        }

    }
}
