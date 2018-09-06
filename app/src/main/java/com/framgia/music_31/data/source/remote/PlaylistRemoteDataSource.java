package com.framgia.music_31.data.source.remote;

import android.content.Context;
import com.framgia.music_31.BuildConfig;
import com.framgia.music_31.data.source.PlaylistDataSource;
import com.framgia.music_31.utils.Constants;

/**
 * Created by hungdev on 02/09/2018.
 */

public class PlaylistRemoteDataSource implements PlaylistDataSource {
    private static PlaylistRemoteDataSource sInstance = null;

    public static synchronized PlaylistRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new PlaylistRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getPlaylists(final PlaylistDataSource.LoadPlaylistCallback callback) {
        String url = Constants.BASE_URL
                + Constants.SELECTION + "?" + Constants.CLIENT_ID + "="
                + BuildConfig.YOUR_API_KEY;
        new GetPlaylistFromUri(callback).execute(url);
    }
}
