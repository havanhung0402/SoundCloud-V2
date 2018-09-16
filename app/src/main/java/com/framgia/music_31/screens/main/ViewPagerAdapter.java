package com.framgia.music_31.screens.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import com.framgia.music_31.data.model.Playlist;
import com.framgia.music_31.screens.discover.DiscoverFragment;
import com.framgia.music_31.screens.more.MoreFragment;
import com.framgia.music_31.screens.mymusic.MyMusicFragment;
import java.util.List;

/**
 * Created by hungdev on 30/08/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public static final int MY_MUSIC_FRAGMENT = 0;
    public static final int DISCOVER_FRAGMENT = 1;
    public static final int MORE_FRAGMENT = 2;
    public static final int TOTAL_FRAGMENT = 3;
    private List<Playlist> mPlaylists;

    public ViewPagerAdapter(FragmentManager fm, List<Playlist> playlists) {
        super(fm);
        mPlaylists = playlists;
        Log.i("Playlist1:", playlists+"");
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case MY_MUSIC_FRAGMENT:
                return MyMusicFragment.newInstance();
            case DISCOVER_FRAGMENT:
                return DiscoverFragment.newInstance(mPlaylists);
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
