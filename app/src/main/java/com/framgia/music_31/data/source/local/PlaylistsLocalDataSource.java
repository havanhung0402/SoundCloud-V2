package com.framgia.music_31.data.source.local;

import com.framgia.music_31.data.source.PlaylistsDataSource;
import com.framgia.music_31.data.source.remote.PlaylistsRemoteDataSource;

/**
 * Created by hungdev on 02/09/2018.
 */

public class PlaylistsLocalDataSource implements PlaylistsDataSource {
    private static PlaylistsLocalDataSource INSTANCE = null;

    public static PlaylistsLocalDataSource getInstance(){
        if (INSTANCE == null){
            INSTANCE = new PlaylistsLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getPlaylists(LoadPlaylistsCallback callback) {

    }
}
