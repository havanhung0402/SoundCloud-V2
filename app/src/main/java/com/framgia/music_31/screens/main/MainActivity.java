package com.framgia.music_31.screens.main;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Playlist;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    public static final String KEY_PLAYLISTS = "key_playlists";
    private BottomNavigationView mBottomNavigationView;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    public static Intent getMainIntent(Context context, List<Playlist> playlists) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putParcelableArrayListExtra(KEY_PLAYLISTS,
                (ArrayList<? extends Parcelable>) playlists);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mViewPager = findViewById(R.id.view_pager);
        mBottomNavigationView = findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        List<Playlist> playlists = getIntent().getParcelableArrayListExtra(KEY_PLAYLISTS);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), playlists);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_my_music:
                mViewPager.setCurrentItem(ViewPagerAdapter.MY_MUSIC_FRAGMENT);
                break;
            case R.id.navigation_discover:
                mViewPager.setCurrentItem(ViewPagerAdapter.DISCOVER_FRAGMENT);
                break;
            case R.id.navigation_more:
                mViewPager.setCurrentItem(ViewPagerAdapter.MORE_FRAGMENT);
                break;
        }
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mBottomNavigationView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
