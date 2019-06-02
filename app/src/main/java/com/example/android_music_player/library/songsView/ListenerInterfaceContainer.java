package com.example.android_music_player.library.songsView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android_music_player.AudioPlayer;
import com.example.android_music_player.MainActivity;
import com.example.android_music_player.R;
import com.example.android_music_player.data.AudioModel;
import com.example.android_music_player.library.AddToPlaylist.AddToPlaylist;
import com.example.android_music_player.playlists.Playlists;
import com.example.android_music_player.playlists.database.PlaylistDataHelper;

public class ListenerInterfaceContainer
{
    public SongsRecyclerAdapter.AdapterListenerInterface s;

    public ListenerInterfaceContainer()
    {
        s = new SongsRecyclerAdapter.AdapterListenerInterface() {
            @Override
            public void playPause(View v, int position) {

                RelativeLayout songLayout = (RelativeLayout) v.getParent();
                TextView songName = songLayout.findViewById(R.id.item_song_name);
                AudioPlayer player = MainActivity.audioPlayer;


                switch (v.getId())
                {
                    case(R.id.play_icon):
                        player.play(MainActivity.mediaData.getSongWithName(songName.getText().toString()));
                        break;


                    case (R.id.pause_icon):
                        player.pause();
                }
            }

            @Override
            public void addToPlaylist(View v, int position)
            {
                RelativeLayout songLayout = (RelativeLayout) v.getParent();
                TextView songName = songLayout.findViewById(R.id.item_song_name);
                AudioModel song = MainActivity.mediaData.getSongWithName(songName.getText().toString());

                Intent intent = new Intent(MainActivity.context, AddToPlaylist.class);
                intent.putExtra("song",song);
                MainActivity.context.startActivity(intent);
//
                Snackbar.make(v, "Added to playlist", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        };
    }
}


    //TODO encapsulate player logic to reuse in dirs
