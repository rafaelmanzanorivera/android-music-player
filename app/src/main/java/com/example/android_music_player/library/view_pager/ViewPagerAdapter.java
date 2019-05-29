package com.example.android_music_player.library.view_pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android_music_player.library.Albums_Fr;
import com.example.android_music_player.library.Songs_Fr;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                return new Albums_Fr();

            case 1:
                return new Songs_Fr();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
