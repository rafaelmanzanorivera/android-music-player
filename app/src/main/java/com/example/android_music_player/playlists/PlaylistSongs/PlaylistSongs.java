package com.example.android_music_player.playlists.PlaylistSongs;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

import com.example.android_music_player.R;
import com.example.android_music_player.playlists.database.PlaylistDataHelper;

public class PlaylistSongs extends AppCompatActivity { //TODO playlist vacia peta

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_playlist_songs);

        android.support.v7.widget.Toolbar tool = findViewById(R.id.toolbar);
        setSupportActionBar(tool);
        tool.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        getSupportActionBar().setTitle(getIntent().getStringExtra("playlist_name"));//TODO get from intent

    }


}
