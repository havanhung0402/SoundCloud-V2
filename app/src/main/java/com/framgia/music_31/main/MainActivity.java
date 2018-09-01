package com.framgia.music_31.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.framgia.music_31.R;
import com.framgia.music_31.data.repository.PlaylistsRepository;
import com.framgia.music_31.data.source.local.PlaylistsLocalDataSource;
import com.framgia.music_31.data.source.remote.PlaylistsRemoteDataSource;
import com.framgia.music_31.screen.discover.DiscoverFragment;
import com.framgia.music_31.screen.discover.DiscoverContract;
import com.framgia.music_31.screen.discover.DiscoverPresenter;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private BottomNavigationView mBottomNavigationView;
    private DiscoverPresenter mDiscoverPresenter;
    private PlaylistsRemoteDataSource mPlaylistsRemoteDataSource;
    private PlaylistsLocalDataSource mPlaylistsLocalDataSource;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // mViewPager = findViewById(R.id.view_pager);
        mBottomNavigationView = findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        //mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        //mViewPager.setAdapter(mViewPagerAdapter);
        //mViewPager.addOnPageChangeListener(this);
        mPlaylistsRemoteDataSource = PlaylistsRemoteDataSource.getInstance(this);
        mPlaylistsLocalDataSource = PlaylistsLocalDataSource.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_color:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_my_music:
                break;
            case R.id.navigation_discover:
                if(fragment == null){
                    fragment = DiscoverFragment.newInstance();
                    initPresenter(fragment);
                }
                break;
            case R.id.navigation_more:
                break;
        }
        return loadFragmnet(fragment);
    }

    private void initPresenter(Fragment fragment) {
        mDiscoverPresenter = new DiscoverPresenter(
                PlaylistsRepository.getInstance(mPlaylistsRemoteDataSource, mPlaylistsLocalDataSource),
                (DiscoverContract.View) fragment);
    }

    private boolean loadFragmnet(Fragment fragment) {
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.view_container, fragment).commit();
            return true;
        }
        return false;
    }
}
