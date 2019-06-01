package com.example.android_music_player.library.songsView;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android_music_player.AudioPlayer;
import com.example.android_music_player.MainActivity;
import com.example.android_music_player.R;
import com.example.android_music_player.data.AudioModel;
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


            }
        };
    }
}


    //TODO encapsulate player logic to reuse in dirs
