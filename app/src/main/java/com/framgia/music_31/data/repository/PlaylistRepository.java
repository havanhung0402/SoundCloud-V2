package com.framgia.music_31.data.repository;

import com.framgia.music_31.data.model.Playlist;
import com.framgia.music_31.data.source.PlaylistDataSource;
import java.util.List;

/**
 * Created by hungdev on 02/09/2018.
 */

public class PlaylistRepository implements PlaylistDataSource {
    private static PlaylistRepository sInstance = null;
    private PlaylistDataSource mPlaylistRemoteDataSource;
    private PlaylistDataSource mPlaylistLocalDataSource;

    private PlaylistRepository(PlaylistDataSource playlistRemoteDataSource,
            PlaylistDataSource playlistLocalDataSource) {
        mPlaylistRemoteDataSource = playlistRemoteDataSource;
        mPlaylistLocalDataSource = playlistLocalDataSource;
    }

    public static synchronized PlaylistRepository getInstance(
            PlaylistDataSource playlistRemoteDataSource,
            PlaylistDataSource playlistLocalDataSource) {
        if (sInstance == null) {
            sInstance = new PlaylistRepository(playlistRemoteDataSource, playlistLocalDataSource);
        }
        return sInstance;
    }

    @Override
    public void getPlaylists(final LoadPlaylistCallback callback) {
        mPlaylistRemoteDataSource.getPlaylists(callback);
    }
}
