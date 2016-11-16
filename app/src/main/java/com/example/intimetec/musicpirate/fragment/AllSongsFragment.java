package com.example.intimetec.musicpirate.fragment;

import android.Manifest;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.intimetec.musicpirate.R;
import com.example.intimetec.musicpirate.adapter.AllSongsListAdapter;
import com.example.intimetec.musicpirate.model.Song;
import com.example.intimetec.musicpirate.service.MusicPirateService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

;

/**
 * Created by intimetec on 10/22/2016.
 */

public class AllSongsFragment extends Fragment{

    private ArrayList<Song> mSongList;
    private ListView mAllSongsView;
   // private LinearLayoutManager mLayoutManager;
    private static Cursor mMusicCursor;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 100;
    private ContentResolver mMusicResolver;
    private Uri mMusicUri;
    private AllSongsListAdapter mAllSongsListAdapter;
    private static MusicPirateService MPService;
    private Intent playIntent;
    private boolean musicBound=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_all_songs, container, false);
        setHasOptionsMenu(true);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        mAllSongsView = (ListView) rootView.findViewById(R.id.all_songs_list);
        mSongList = new ArrayList<>();
        mMusicResolver = getActivity().getContentResolver();
        mMusicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
      //  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED)
        mMusicCursor = mMusicResolver.query(mMusicUri, null, null, null, null);
        else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            }
        mSongList = getAllSongsList();
        mAllSongsListAdapter = new AllSongsListAdapter(getContext(), mSongList);
        mAllSongsView.setAdapter(mAllSongsListAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMusicCursor = mMusicResolver.query(mMusicUri, null, null, null, null);
                    mSongList = getAllSongsList();
                    mAllSongsListAdapter = new AllSongsListAdapter(getContext(), mSongList);
                    mAllSongsView.setAdapter(mAllSongsListAdapter);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

        //connect to the service
    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicPirateService.MusicBinder binder = (MusicPirateService.MusicBinder)service;
            //get service
            MPService = binder.getService();
            //pass list
            MPService.setSongs(mSongList);
            mAllSongsListAdapter.setMusicPlayerService(MPService);
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        if(playIntent==null){
            playIntent = new Intent(getActivity(), MusicPirateService.class);
            getActivity().bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            getActivity().startService(playIntent);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId()) {
            case R.id.action_shuffle:
                //shuffle
                break;
            case R.id.action_end:
                getActivity().stopService(playIntent);
                MPService=null;
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Song> getAllSongsList() {
        ArrayList<Song> songList = new ArrayList<>();
        if (mMusicCursor != null && mMusicCursor.moveToFirst()) {
            //get columns
            int titleColumn = mMusicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = mMusicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = mMusicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            //add songs to list
            do {
                long thisId = mMusicCursor.getLong(idColumn);
                String thisTitle = mMusicCursor.getString(titleColumn);
                String thisArtist = mMusicCursor.getString(artistColumn);
                songList.add(new Song(thisId, thisTitle, thisArtist));
            }
            while (mMusicCursor.moveToNext());
        }

        return orderSongList(songList);
    }

    private ArrayList<Song> orderSongList(ArrayList<Song> songList) {
        Collections.sort(songList, new Comparator<Song>(){
            public int compare(Song a, Song b){
                return a.getTitle().compareTo(b.getTitle());
            }
        });
        return songList;
    }

    @Override
    public void onDestroy() {
        getActivity().stopService(playIntent);
        MPService=null;
        super.onDestroy();
    }

}
