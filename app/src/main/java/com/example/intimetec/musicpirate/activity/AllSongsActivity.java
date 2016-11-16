package com.example.intimetec.musicpirate.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.intimetec.musicpirate.MusicPirateActivity;
import com.example.intimetec.musicpirate.R;
import com.example.intimetec.musicpirate.fragment.AllSongsFragment;

/**
 * Created by intimetec on 10/22/2016.
 */

public class AllSongsActivity extends MusicPirateActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_songs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setUpToolbar(toolbar, getString(R.string.app_name));


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.all_songs_container);

        if (fragment == null) {
            fragment = new AllSongsFragment();
            fm.beginTransaction()
                    .add(R.id.all_songs_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
