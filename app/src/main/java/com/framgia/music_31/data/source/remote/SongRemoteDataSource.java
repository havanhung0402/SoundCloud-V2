package com.framgia.music_31.data.source.remote;

import android.util.Log;
import com.framgia.music_31.BuildConfig;
import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.data.source.CallBack;
import com.framgia.music_31.data.source.SongDataSource;
import com.framgia.music_31.utils.Constants;
import java.util.List;

/**
 * Created by hungdev on 07/09/2018.
 */

public class SongRemoteDataSource implements SongDataSource.RemoteDataSource {

    private static SongRemoteDataSource sInstance;

    private SongRemoteDataSource() {

    }

    public static synchronized SongRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new SongRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getSongs(String baseUrl, String action, CallBack<List<Song>> callBack) {
        String url = null;
        if(action.equals("action.GENRE")){
            url = baseUrl + Constants.AMPERSAND + Constants.CLIENT_ID + BuildConfig.YOUR_API_KEY;
            new GetSongsFromUri(callBack).execute(url);
        }else if (action.equals("action.PLAYLIST")){
            url = baseUrl+ Constants.QUESTION_MARK
                    + Constants.CLIENT_ID
                    + BuildConfig.YOUR_API_KEY;
            new GetSongFromPlaylist(callBack).execute(url);
        }
    }
}
