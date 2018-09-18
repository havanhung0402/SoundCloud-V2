package com.framgia.music_31.data.source;

import com.framgia.music_31.data.model.Song;
import java.util.List;

/**
 * Created by hungdev on 07/09/2018.
 */

public interface SongDataSource {

    interface RemoteDataSource {
        void getSongs(String genre, String action, CallBack<List<Song>> callBack);
    }

    interface LocalDataSource {
        void getSongsLocal(CallBack<List<Song>> callBack);
    }
}
