package com.framgia.music_31.data.repository;

import android.util.Log;
import com.framgia.music_31.data.model.Playlist;
import com.framgia.music_31.data.source.PlaylistsDataSource;
import java.util.List;

/**
 * Created by hungdev on 02/09/2018.
 */

public class PlaylistsRepository implements PlaylistsDataSource {
    private static PlaylistsRepository INSTANCE = null;
    private PlaylistsDataSource mPlaylistsRemoteDataSource;
    private PlaylistsDataSource mPlaylistsLocalDataSource;

    public PlaylistsRepository(PlaylistsDataSource playlistsRemoteDataSource,
            PlaylistsDataSource playlistsLocalDataSource) {
        mPlaylistsRemoteDataSource = playlistsRemoteDataSource;
        mPlaylistsLocalDataSource = playlistsLocalDataSource;
    }

    public static PlaylistsRepository getInstance(PlaylistsDataSource playlistsRemoteDataSource,
            PlaylistsDataSource playlistsLocalDataSource){
        if (INSTANCE == null){
            INSTANCE = new PlaylistsRepository(playlistsRemoteDataSource, playlistsLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getPlaylists(final LoadPlaylistsCallback callback) {
        mPlaylistsRemoteDataSource.getPlaylists(new LoadPlaylistsCallback() {
            @Override
            public void onPlaylistsLoaded(List<Playlist> playlists) {
                callback.onPlaylistsLoaded(playlists);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

}
