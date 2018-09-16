package com.framgia.music_31.screens.splash;

import com.framgia.music_31.data.model.Playlist;
import com.framgia.music_31.data.repository.DiscoverRepository;
import com.framgia.music_31.data.source.CallBack;
import java.util.List;

/**
 * Created by hungdev on 16/09/2018.
 */

public class SplashPresenter implements SplashContract.Presenter{
    private SplashContract.View mView;
    private DiscoverRepository mDiscoverRepository;

    public SplashPresenter(DiscoverRepository discoverRepository) {
        mDiscoverRepository = discoverRepository;
    }

    @Override
    public void start() {
        loadPlaylist();
    }

    @Override
    public void setView(SplashContract.View view) {
        mView= view;
    }

    @Override
    public void loadPlaylist() {
        mDiscoverRepository.getPlaylists(new CallBack<List<Playlist>>() {
            @Override
            public void onSusscess(List<Playlist> datas) {
                mView.onGetPlayListSuccess(datas);
            }

            @Override
            public void onError(Exception e) {
                mView.onGetDataError(e);
            }
        });
    }
}
