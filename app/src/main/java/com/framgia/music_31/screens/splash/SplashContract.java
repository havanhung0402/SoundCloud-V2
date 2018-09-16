package com.framgia.music_31.screens.splash;

import com.framgia.music_31.BasePresenter;
import com.framgia.music_31.data.model.Playlist;
import java.util.List;

/**
 * Created by hungdev on 16/09/2018.
 */

public interface SplashContract {

    interface View {

        void onGetPlayListSuccess(List<Playlist> playlists);

        void onGetDataError(Exception e);
    }

    interface Presenter extends BasePresenter<View> {

        void loadPlaylist();
    }
}
