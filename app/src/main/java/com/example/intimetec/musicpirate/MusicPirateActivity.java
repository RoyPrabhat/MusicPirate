package com.example.intimetec.musicpirate;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by intimetec on 7/31/2016.
 */
public class MusicPirateActivity extends AppCompatActivity {
    private static MusicPirateActivity appInstance;

    public static MusicPirateActivity getInstance() {
        appInstance = new MusicPirateActivity();
        return appInstance;
    }


    protected void setUpToolbar(Toolbar toolbar, String title) {

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                toolbar.setElevation(getResources().getDimension(R.dimen.toolbar_elevation));
            }
        }
    }

}
