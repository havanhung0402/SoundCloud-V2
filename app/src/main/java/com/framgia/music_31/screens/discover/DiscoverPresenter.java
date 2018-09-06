package com.framgia.music_31.screens.discover;

import android.util.Log;
import com.framgia.music_31.data.model.Playlist;
import com.framgia.music_31.data.repository.PlaylistRepository;
import com.framgia.music_31.data.source.PlaylistDataSource;
import java.util.List;

/**
 * Created by hungdev on 02/09/2018.
 */

public class DiscoverPresenter
        implements DiscoverContract.Presenter, PlaylistDataSource.LoadPlaylistCallback {
    private PlaylistRepository mPlaylistRepository;
    private DiscoverContract.View mView;

    public DiscoverPresenter(PlaylistRepository playlistRepository) {
        mPlaylistRepository = playlistRepository;
    }

    @Override
    public void start() {
        loadPlaylist();
    }

    @Override
    public void setView(DiscoverContract.View view) {
        mView = view;
    }

    @Override
    public void loadPlaylist() {
        mPlaylistRepository.getPlaylists(this);
    }

    @Override
    public void onPlaylistLoaded(List<Playlist> playlists) {
        mView.onGetPlayListSuccess(playlists);
    }

    @Override
    public void onDataNotAvailable() {

    }

    @Override
    public void onError(Exception e) {

    }
}
