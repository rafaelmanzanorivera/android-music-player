package com.example.android_music_player;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.android_music_player.data.MediaData;
import com.example.android_music_player.library.songsView.SongsRecyclerAdapter;
import com.example.android_music_player.library.view_pager.ViewPagerAdapter;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static MediaData mediaData;
    public static AudioPlayer audioPlayer = new AudioPlayer();
    private static final int PERMISSIONS_REQUEST_READ_STORAGE= 0;
    public static SongsRecyclerAdapter songsRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPermissions();

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

        //get media data TODO in background
        mediaData.getSongsList();
        mediaData.getDirectoriesList();

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

    //TODO en caso de que no te los den
    private void getPermissions()
    {
// Here, thisActivity is the current activity
        if (this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST_READ_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }




}
