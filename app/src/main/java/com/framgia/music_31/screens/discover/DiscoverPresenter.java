package com.framgia.music_31.screens.discover;

import android.app.AlertDialog;
import com.framgia.music_31.data.model.Genre;
import com.framgia.music_31.data.model.Playlist;
import com.framgia.music_31.data.repository.DiscoverRepository;
import com.framgia.music_31.data.source.CallBack;
import com.framgia.music_31.data.source.DiscoverDataSource;
import java.util.List;

/**
 * Created by hungdev on 02/09/2018.
 */

public class DiscoverPresenter implements DiscoverContract.Presenter {
    private DiscoverRepository mDiscoverRepository;
    private DiscoverContract.View mView;

    public DiscoverPresenter(DiscoverRepository discoverRepository) {
        mDiscoverRepository = discoverRepository;
    }

    @Override
    public void start() {
        loadPlaylist();
        getGenre();
    }

    @Override
    public void setView(DiscoverContract.View view) {
        mView = view;
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

            }
        });
    }

    @Override
    public void getGenre() {
        mDiscoverRepository.getGenre(new CallBack<List<Genre>>() {
            @Override
            public void onSusscess(List<Genre> datas) {
                mView.onGetGenreSuccess(datas);
            }

            @Override
            public void onError(Exception e) {
                mView.onGetDataError(e);
            }
        });
    }
}
