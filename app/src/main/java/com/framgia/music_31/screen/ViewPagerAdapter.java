package com.framgia.music_31.screen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.framgia.music_31.screen.discover.DiscoverFragment;
import com.framgia.music_31.screen.more.MoreFragment;
import com.framgia.music_31.screen.mymusic.MyMusicFragment;

/**
 * Created by hungdev on 30/08/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public static final int MY_MUSIC_FRAGMENT = 0;
    public static final int DISCOVER_FRAGMENT = 1;
    public static final int MORE_FRAGMENT = 2;
    public static final int TOTAL_FRAGMENT = 3;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case MY_MUSIC_FRAGMENT:
                return MyMusicFragment.newInstance();
            case DISCOVER_FRAGMENT:
                return DiscoverFragment.newInstance();
            case MORE_FRAGMENT:
                return MoreFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TOTAL_FRAGMENT;
    }
}
