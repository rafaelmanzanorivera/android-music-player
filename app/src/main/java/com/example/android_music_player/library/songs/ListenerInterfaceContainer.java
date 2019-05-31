package com.example.android_music_player.library.songs;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.android_music_player.AudioPlayer;
import com.example.android_music_player.MainActivity;
import com.example.android_music_player.R;

public class ListenerInterface
{
    public SongsRecyclerAdapter.AdapterListenerInterface s;

    public ListenerInterface()
    {
        s = new SongsRecyclerAdapter.AdapterListenerInterface() {
            @Override
            public void classOnClick(View v, int position) {
                Log.d("midebug",v +" " + position + " ");

                //TODO implement play/pause

                RelativeLayout songLayout = (RelativeLayout) v.getParent();
                ImageView playIcon = songLayout.findViewById(R.id.play_icon);
                ImageView pauseIcon = songLayout.findViewById(R.id.pause_icon);

                AudioPlayer player = MainActivity.audioPlayer;

                if( playIcon.getVisibility() == View.VISIBLE )
                {
                    playIcon.setVisibility(View.INVISIBLE);
                    pauseIcon.setVisibility(View.VISIBLE);

                    if(player.playingSongLayout != null && player.playingSongLayout != songLayout)
                    {
                        player.playingSongLayout.findViewById(R.id.play_icon).setVisibility(View.VISIBLE);
                        player.playingSongLayout.findViewById(R.id.pause_icon).setVisibility(View.INVISIBLE);
                    }

                    player.play(MainActivity.mediaData.getSongsList().get(position));
                    player.playingSongLayout = songLayout;
                }
                else
                {
                    playIcon.setVisibility(View.VISIBLE);
                    pauseIcon.setVisibility(View.INVISIBLE);

                    player.pause();
                }
            }

            @Override
            public void daysOnClick(View v, int position) {

            }
        };
    }
}


    //TODO encapsulate player logic to reuse in dirs
