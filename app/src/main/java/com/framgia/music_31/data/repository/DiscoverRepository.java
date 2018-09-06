package com.framgia.music_31.data.repository;

import com.framgia.music_31.data.source.CallBack;
import com.framgia.music_31.data.source.DiscoverDataSource;
import com.framgia.music_31.data.source.local.DiscoverLocalDataSource;
import com.framgia.music_31.data.source.remote.DiscoverRemoteDataSource;

/**
 * Created by hungdev on 02/09/2018.
 */

public class DiscoverRepository
        implements DiscoverDataSource.RemoteDataSource, DiscoverDataSource.LocalDataSource {
    private static DiscoverRepository sInstance;
    private DiscoverDataSource.RemoteDataSource mDiscoverRemoteDataSource;
    private DiscoverDataSource.LocalDataSource mDiscoverLocalDataSource;

    private DiscoverRepository(DiscoverRemoteDataSource discoverRemoteDataSource,
            DiscoverLocalDataSource disoverLocalDataSource) {
        mDiscoverRemoteDataSource = discoverRemoteDataSource;
        mDiscoverLocalDataSource = disoverLocalDataSource;
    }

    public static synchronized DiscoverRepository getInstance(
            DiscoverRemoteDataSource discoverRemoteDataSource,
            DiscoverLocalDataSource discoverLocalDataSource) {
        if (sInstance == null) {
            sInstance = new DiscoverRepository(discoverRemoteDataSource, discoverLocalDataSource);
        }
        return sInstance;
    }

    @Override
    public void getPlaylists(CallBack callback) {
        mDiscoverRemoteDataSource.getPlaylists(callback);
    }

    @Override
    public void getGenre(CallBack callback) {
        mDiscoverLocalDataSource.getGenre(callback);
    }
}
