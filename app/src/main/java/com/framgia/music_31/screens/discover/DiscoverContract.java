package com.framgia.music_31.screens.discover;

import com.framgia.music_31.BasePresenter;
import com.framgia.music_31.BaseView;
import com.framgia.music_31.data.model.Playlist;
import java.util.List;

/**
 * Created by hungdev on 02/09/2018.
 */

public interface DiscoverContract {

    interface View {
        void onGetPlayListSuccess(List<Playlist> playlists);
    }

    interface Presenter extends BasePresenter<View> {
        void loadPlaylist();
    }
}
