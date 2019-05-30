package com.example.android_music_player;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.android_music_player.data.MediaData;
import com.example.android_music_player.library.view_pager.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static MediaData mediaData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaData = new MediaData(this);

        //View pager conf
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),this);

        ViewPager pager = findViewById(R.id.viewpager);
        pager.setAdapter(adapter);


        //Custom toolbar conf
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Tab layout conf
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(pager);

        //Nav bar
        DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationdrawer_view);
        navigationView.setNavigationItemSelectedListener(this);//decirle al menú que la actividad va a controlar los eventos de seleccionar un ítem.

        //get media data
        mediaData.getSongsList();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id)
        {
            case R.id.navigation_library:
                break;

            case R.id.navigation_playlists:
                //TODO intent to playlists actv
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

    //Checks if menu is open before closing the app
    public void onBackPressed()
    {
        DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);

        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}
