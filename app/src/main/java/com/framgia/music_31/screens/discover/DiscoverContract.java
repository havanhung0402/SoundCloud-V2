package com.framgia.music_31.screens.discover;

import com.framgia.music_31.BasePresenter;
import com.framgia.music_31.data.model.Genre;
import com.framgia.music_31.data.model.Playlist;
import java.util.List;

/**
 * Created by hungdev on 02/09/2018.
 */

public interface DiscoverContract {

    interface View {
        void onGetPlayListSuccess(List<Playlist> playlists);

        void onGetDataError(Exception e);

        void onGetGenreSuccess(List<Genre> genres);
    }

    interface Presenter extends BasePresenter<View> {
        void loadPlaylist();

        void getGenre();
    }
}
