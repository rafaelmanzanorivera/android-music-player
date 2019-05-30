package com.example.android_music_player.data;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.android_music_player.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class MediaData
{

    private List<AudioModel> songsList;
    private Context context;

    public MediaData(final Context c)
    {
        context = c;
        songsList = null;
    }


    public List<AudioModel> getSongsList()
    {
        if(songsList != null)
            return songsList;
        else
        {
            this.getAllAudioFromDevice(context);
            return songsList;
        }
    }




    // Method to read all the audio/MP3 files.
    private void getAllAudioFromDevice(final Context context) {
        songsList = new ArrayList<>();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.AudioColumns.DATA,MediaStore.Audio.AudioColumns.TITLE ,MediaStore.Audio.AudioColumns.ALBUM, MediaStore.Audio.ArtistColumns.ARTIST,};
        Cursor c = context.getContentResolver().query(uri,
                projection,
                null,
                null,
                null);

        if (c != null) {
            while (c.moveToNext()) {
                // Create a model object.
                AudioModel audioModel = new AudioModel();

                String path = c.getString(0);   // Retrieve path.
                String name = c.getString(1);   // Retrieve name.
                String album = c.getString(2);  // Retrieve album name.
                String artist = c.getString(3); // Retrieve artist name.

                // Set data to the model object.
                audioModel.setaName(name);
                audioModel.setaAlbum(album);
                audioModel.setaArtist(artist);
                audioModel.setaPath(path);

                Log.e("Name :" + name, " Album :" + album);
                Log.e("Path :" + path, " Artist :" + artist);

                // Add the model object to the list .
                songsList.add(audioModel);
            }
            c.close();
        }
    }
}
