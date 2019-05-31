package com.example.android_music_player;

import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.widget.RelativeLayout;

import com.example.android_music_player.data.AudioModel;

public class AudioPlayer
{
    //TODO notify ended song
    public MediaPlayer mediaPlayer;
    public AudioModel playingSong;
    public boolean isPaused = false;

    public AudioPlayer()
    {
        mediaPlayer = new MediaPlayer();
    }

    public void play(AudioModel song)
    {
        //TODO simplify logic (or comment at least)
        if(mediaPlayer.isPlaying())
        {
            //Different song
            if(!song.getaName().equals(playingSong.getaName()))
            {
                //Stop other and start new
                mediaPlayer.reset();

                try {
                    mediaPlayer.setDataSource(song.getaPath());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    isPaused = false;
                    playingSong = song;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else
                mediaPlayer.start();
        }
        else if (playingSong != null)
        {
            if(!song.getaName().equals(playingSong.getaName()))
            {
                //Stop other and start new
                mediaPlayer.reset();

                try {
                    mediaPlayer.setDataSource(song.getaPath());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    isPaused = false;
                    playingSong = song;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
                mediaPlayer.start();
        }
        else
        {
            //Stop other and start new
            mediaPlayer.reset();

            try {
                mediaPlayer.setDataSource(song.getaPath());
                mediaPlayer.prepare();
                mediaPlayer.start();
                isPaused = false;
                playingSong = song;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        isPaused = false;
        MainActivity.songsRecyclerAdapter.notifyDataSetChanged();

    }

    public void pause()
    {
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
            isPaused = true;

        }
        MainActivity.songsRecyclerAdapter.notifyDataSetChanged();

    }


}
