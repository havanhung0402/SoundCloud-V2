package com.framgia.music_31.data.source.local;

import com.framgia.music_31.data.source.PlaylistDataSource;

/**
 * Created by hungdev on 02/09/2018.
 */

public class PlaylistLocalDataSource implements PlaylistDataSource {
    private static PlaylistLocalDataSource sInstance = null;

    public static synchronized PlaylistLocalDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new PlaylistLocalDataSource();
        }
        return sInstance;
    }

    @Override
    public void getPlaylists(LoadPlaylistCallback callback) {

    }
}
