package com.example.android_music_player;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android_music_player.library.view_pager.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View pager conf
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),this);

        ViewPager pager = findViewById(R.id.viewpager);
        pager.setAdapter(adapter);


        //Toolbar conf
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(pager);

    }
}
