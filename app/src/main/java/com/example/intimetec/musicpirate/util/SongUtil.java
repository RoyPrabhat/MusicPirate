package com.example.intimetec.musicpirate.util;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.intimetec.musicpirate.MusicPirateActivity;
import com.example.intimetec.musicpirate.model.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by intimetec on 10/24/2016.
 */

public class SongUtil {




    public static ArrayList<Song> sortSongsListAlphabetically(ArrayList<Song> songList) {

        Collections.sort(songList, new Comparator<Song>() {
            public int compare(Song a, Song b) {
                return a.getTitle().compareTo(b.getTitle());
            }
        });
        return songList;
    }
}
