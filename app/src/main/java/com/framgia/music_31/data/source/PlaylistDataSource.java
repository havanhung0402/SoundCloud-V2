package com.framgia.music_31.data.source;

import com.framgia.music_31.data.model.Playlist;
import java.util.List;

/**
 * Created by hungdev on 02/09/2018.
 */

public interface PlaylistDataSource {

    interface LoadPlaylistCallback {

        void onPlaylistLoaded(List<Playlist> playlists);

        void onDataNotAvailable();

        void onError(Exception e);
    }

    interface GetPlaylistCallback {

        void onPlaylistsLoaded(Playlist playlist);

        void onDataNotAvailable();
    }

    void getPlaylists(LoadPlaylistCallback callback);
}
