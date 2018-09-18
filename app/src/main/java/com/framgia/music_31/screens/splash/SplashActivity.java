package com.framgia.music_31.screens.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Playlist;
import com.framgia.music_31.data.repository.DiscoverRepository;
import com.framgia.music_31.data.source.local.DiscoverLocalDataSource;
import com.framgia.music_31.data.source.remote.DiscoverRemoteDataSource;
import com.framgia.music_31.screens.main.MainActivity;
import java.util.List;

public class SplashActivity extends AppCompatActivity implements SplashContract.View{

    private SplashPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mPresenter = new SplashPresenter(DiscoverRepository.getInstance(DiscoverRemoteDataSource.getInstance(),
                DiscoverLocalDataSource.getInstance()));
        mPresenter.setView(this);
        mPresenter.start();
    }

    @Override
    public void onGetPlayListSuccess(List<Playlist> playlists) {
        startActivity(MainActivity.getMainIntent(this, playlists));
        finish();
    }

    @Override
    public void onGetDataError(Exception e) {
        finish();
    }
}
