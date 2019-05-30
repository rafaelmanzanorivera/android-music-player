package com.example.android_music_player.library.view_pager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android_music_player.R;
import com.example.android_music_player.library.directories.Albums_Fr;
import com.example.android_music_player.library.songs.Songs_Fr;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context m_context;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        m_context = context;
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

    public CharSequence getPageTitle(int position)
    {
        switch(position)
        {
            case 0:
                return m_context.getResources().getText(R.string.library_tab);
            case 1:
                return m_context.getResources().getText(R.string.songs_tab);
        }
        return null;
    }

}
