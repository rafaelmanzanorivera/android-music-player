package com.example.android_music_player.library.directoriesView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android_music_player.MainActivity;
import com.example.android_music_player.R;
import com.example.android_music_player.library.songsView.ListenerInterfaceContainer;
import com.example.android_music_player.library.songsView.SongsRecyclerAdapter;


public class DirectorySongs extends AppCompatActivity {

    int albumPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_songs);

        android.support.v7.widget.Toolbar tool = findViewById(R.id.toolbar);
        setSupportActionBar(tool);
        tool.setTitleTextColor(getResources().getColor(R.color.colorAccent));

        Intent intent = getIntent();
        int albumPos = intent.getIntExtra("position", 0);

        RecyclerView recyclerView = findViewById(R.id.album_songs_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getSupportActionBar().setTitle(MainActivity.mediaData.getDirectoriesList().get(albumPos).getDirectoryName());

        ListenerInterfaceContainer container = new ListenerInterfaceContainer();

        MainActivity.songsRecyclerAdapter = new SongsRecyclerAdapter(MainActivity.mediaData.getDirectoriesList().get(albumPos).getSongs(),this,container.s);
        recyclerView.setAdapter(MainActivity.songsRecyclerAdapter);

    }
}
