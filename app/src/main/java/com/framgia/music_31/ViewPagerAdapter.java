package com.framgia.music_31;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungdev on 30/08/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public static final int MY_MUSIC_FRAGMENT = 0;
    public static final int DISCOVER_FRAGMENT = 1;
    public static final int MORE_FRAGMENT = 2;
    public static final int TOTAL_FRAGMENT = 3;
    List<Fragment> mFlagmentList = new ArrayList<>();
    List<String> mFlagmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case MY_MUSIC_FRAGMENT:
                return MyMuiscFragment.newInstance();
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
