package com.framgia.music_31.data.source.remote;

import com.framgia.music_31.BuildConfig;
import com.framgia.music_31.data.source.CallBack;
import com.framgia.music_31.data.source.DiscoverDataSource;
import com.framgia.music_31.utils.Constants;

/**
 * Created by hungdev on 02/09/2018.
 */

public class DiscoverRemoteDataSource implements DiscoverDataSource.RemoteDataSource {
    private static DiscoverRemoteDataSource sInstance;

    public static synchronized DiscoverRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new DiscoverRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getPlaylists(CallBack callback) {
        String url = Constants.BASE_URL
                + Constants.SELECTION + "?" + Constants.CLIENT_ID + "="
                + BuildConfig.YOUR_API_KEY;
        new GetPlaylistFromUri(callback).execute(url);
    }
}
