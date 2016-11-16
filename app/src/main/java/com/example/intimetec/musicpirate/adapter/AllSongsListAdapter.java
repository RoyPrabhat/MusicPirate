package com.example.intimetec.musicpirate.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.intimetec.musicpirate.R;
import com.example.intimetec.musicpirate.fragment.AllSongsFragment;
import com.example.intimetec.musicpirate.model.Song;
import com.example.intimetec.musicpirate.service.MusicPirateService;

import java.util.ArrayList;

/**
 * Created by intimetec on 10/24/2016.
 */

public class AllSongsListAdapter extends BaseAdapter{

    private ArrayList<Song> songs;
    private LayoutInflater songInf;
    private MusicPirateService MPService;

    public AllSongsListAdapter(Context c, ArrayList<Song> theSongs){
        songs=theSongs;
        songInf=LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //map to song layout
        CardView songLay = (CardView)songInf.inflate
                (R.layout.layout_song_item, parent, false);
        //get title and artist views
        TextView songView = (TextView)songLay.findViewById(R.id.song_title);
        TextView artistView = (TextView)songLay.findViewById(R.id.song_artist);
        //get song using position
        Song currSong = songs.get(position);
        //get title and artist strings
        songView.setText(currSong.getTitle());
        artistView.setText(currSong.getArtist());
        //set position as tag
        songLay.setTag(position);

        songLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               songPicked(v);
            }
        });

        return songLay;
    }

    public void songPicked(View view){
        MPService.setSong(Integer.parseInt(view.getTag().toString()));
        MPService.playSong();
    }

    public void setMusicPlayerService(MusicPirateService mpService) {
        this.MPService = mpService;
    }
}
