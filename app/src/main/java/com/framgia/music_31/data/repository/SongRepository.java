package com.framgia.music_31.data.repository;

import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.data.source.CallBack;
import com.framgia.music_31.data.source.SongDataSource;
import com.framgia.music_31.data.source.local.SongLocalDataSource;
import com.framgia.music_31.data.source.remote.SongRemoteDataSource;
import java.util.List;

/**
 * Created by hungdev on 02/09/2018.
 */

public class SongRepository
        implements SongDataSource.RemoteDataSource, SongDataSource.LocalDataSource {

    private static SongRepository sInstance;
    private SongDataSource.LocalDataSource mSongLocalDataSource;
    private SongDataSource.RemoteDataSource mSongRemoteDataSource;

    private SongRepository(SongRemoteDataSource songRemoteDataSource,
            SongLocalDataSource songLocalDataSource) {
        mSongRemoteDataSource = songRemoteDataSource;
        mSongLocalDataSource = songLocalDataSource;
    }

    public static SongRepository getsInstance(SongRemoteDataSource songRemoteDataSource,
            SongLocalDataSource songLocalDataSource) {
        if (sInstance == null) {
            sInstance = new SongRepository(songRemoteDataSource, songLocalDataSource);
        }
        return sInstance;
    }

    @Override
    public void getSongs(String genreParam, CallBack<List<Song>> callBack) {
        mSongRemoteDataSource.getSongs(genreParam, callBack);
    }

    @Override
    public void getSongsLocal(CallBack<List<Song>> callBack) {
        mSongLocalDataSource.getSongsLocal(callBack);
    }
}
