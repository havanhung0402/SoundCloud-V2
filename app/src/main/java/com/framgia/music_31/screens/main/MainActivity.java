package com.framgia.music_31.screens.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.framgia.music_31.R;
import com.framgia.music_31.screens.discover.DiscoverFragment;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView mBottomNavigationView;
    private DiscoverFragment mDiscoverFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBottomNavigationView = findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
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
        switch (item.getItemId()) {
            case R.id.navigation_my_music:
                break;
            case R.id.navigation_discover:
                if(mDiscoverFragment == null){
                    mDiscoverFragment = DiscoverFragment.newInstance();
                }
                break;
            case R.id.navigation_more:
                break;
        }
        return replaceFragment(mDiscoverFragment);
    }

    private boolean replaceFragment(DiscoverFragment fragment) {
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.view_container, fragment).commit();
            return true;
        }
        return false;
    }
}
