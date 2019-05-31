package com.example.android_music_player;

import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.widget.RelativeLayout;

import com.example.android_music_player.data.AudioModel;

public class AudioPlayer
{
    public MediaPlayer mediaPlayer;
    public AudioModel playingSong;
    public RelativeLayout playingSongLayout;//TODO move to main

    public AudioPlayer()
    {
        mediaPlayer = new MediaPlayer();
    }

    public void play(AudioModel song)
    {

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
                playingSong = song;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void pause()
    {
        if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }


}
