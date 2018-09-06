package com.framgia.music_31.data.source;

import com.framgia.music_31.data.model.Genre;
import com.framgia.music_31.data.model.Playlist;
import java.util.List;

/**
 * Created by hungdev on 02/09/2018.
 */

public interface DiscoverDataSource {

    interface RemoteDataSource {

        void getPlaylists(CallBack<List<Playlist>> callback);
    }

    interface LocalDataSource {

        void getGenre(CallBack<List<Genre>> callback);
    }
}
