package com.framgia.music_31.data.source;

import com.framgia.music_31.data.model.Playlist;
import java.util.List;

/**
 * Created by hungdev on 02/09/2018.
 */

public interface PlaylistsDataSource {

    interface LoadPlaylistsCallback {

        void onPlaylistsLoaded(List<Playlist> playlists);
        void onDataNotAvailable();
    }

    interface GetPlaylistCallback {

        void onPlaylistsLoaded(Playlist playlists);
        void onDataNotAvailable();
    }

    void getPlaylists(LoadPlaylistsCallback callback);
}
