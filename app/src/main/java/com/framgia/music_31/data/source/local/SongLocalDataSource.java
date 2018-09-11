package com.framgia.music_31.data.source.local;

import android.content.Context;
import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.data.source.CallBack;
import com.framgia.music_31.data.source.SongDataSource;
import com.framgia.music_31.data.source.remote.DiscoverRemoteDataSource;
import com.framgia.music_31.utils.Constants;
import java.util.List;

/**
 * Created by hungdev on 07/09/2018.
 */

public class SongLocalDataSource implements SongDataSource.LocalDataSource{
    private static SongLocalDataSource sInstance;
    private Context mContext;

    public SongLocalDataSource(Context context) {
        mContext = context;
    }

    public static synchronized SongLocalDataSource getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SongLocalDataSource(context);
        }
        return sInstance;
    }

    @Override
    public void getSongsLocal(CallBack<List<Song>> callBack) {
        new GetSongsLocal(mContext, callBack).execute();
    }
}
