package com.example.android_music_player.playlists;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.android_music_player.MainActivity;
import com.example.android_music_player.R;
import com.example.android_music_player.data.MediaData;

public class Playlists extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {


    public static MediaData mediaData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlists);

        android.support.v7.widget.Toolbar tool = findViewById(R.id.toolbar);
        setSupportActionBar(tool);
        tool.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        getSupportActionBar().setTitle("Playlists");

        //Nav bar
        DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,tool,0,0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationdrawer_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.navigation_playlists);

        mediaData = new MediaData(this);
        mediaData.getSongsList();

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id)
        {
            case R.id.navigation_library:
                finish();
                break;

            case R.id.navigation_playlists:
                break;

            case R.id.navigation_options:
                break;

            case R.id.navigation_contact:
                break;

            case R.id.navigation_help:
                break;

        }

        DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
