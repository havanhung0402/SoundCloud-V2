package com.framgia.music_31.data.source.local;

import com.framgia.music_31.data.source.SongDataSource;
import com.framgia.music_31.data.source.remote.DiscoverRemoteDataSource;

/**
 * Created by hungdev on 07/09/2018.
 */

public class SongLocalDataSource implements SongDataSource.LocalDataSource{
    private static SongLocalDataSource sInstance;

    public static synchronized SongLocalDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new SongLocalDataSource();
        }
        return sInstance;
    }
}
