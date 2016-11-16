package com.example.intimetec.musicpirate.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.intimetec.musicpirate.R;
import com.example.intimetec.musicpirate.activity.AllSongsActivity;

/**
 * Created by intimetec on 8/9/2016.
 */
public class MainFragment extends Fragment {

    private LinearLayout mAllSongsLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        mAllSongsLayout = (LinearLayout) rootView.findViewById(R.id.all_songs);
        mAllSongsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allSongsActivity = new Intent(getContext(), AllSongsActivity.class);
                startActivity(allSongsActivity);
            }
        });
    }
}