package com.example.android_music_player.library.AddToPlaylist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.example.android_music_player.R;
import com.example.android_music_player.playlists.database.PlaylistDataHelper;

public class AddToPlaylist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_playlist);

        android.support.v7.widget.Toolbar tool = findViewById(R.id.toolbar_pl);
        setSupportActionBar(tool);
        tool.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        getSupportActionBar().setTitle("Playlists");
    }


}
