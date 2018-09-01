package com.framgia.music_31.screen.discover;

import android.util.Log;
import com.framgia.music_31.data.model.Playlist;
import com.framgia.music_31.data.repository.PlaylistsRepository;
import com.framgia.music_31.data.source.PlaylistsDataSource;
import java.util.List;

/**
 * Created by hungdev on 02/09/2018.
 */

public class DiscoverPresenter implements DiscoverContract.Presenter,
        PlaylistsDataSource.LoadPlaylistsCallback {
    private PlaylistsRepository mPlaylistsRepository;
    private DiscoverContract.View mView;

    public DiscoverPresenter(PlaylistsRepository playlistsRepository,
            DiscoverContract.View view) {
        mPlaylistsRepository = playlistsRepository;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadPlaylists();
    }

    @Override
    public void loadPlaylists() {
        mPlaylistsRepository.getPlaylists(this);
    }

    @Override
    public void onPlaylistsLoaded(List<Playlist> playlists) {
        Log.d("Playlist", playlists+"");
        mView.showPlaylist(playlists);
    }

    @Override
    public void onDataNotAvailable() {

    }
}
