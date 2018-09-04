package com.framgia.music_31.screen.discover;

import com.framgia.music_31.BasePresenter;
import com.framgia.music_31.BaseView;
import com.framgia.music_31.data.model.Playlist;
import java.util.List;

/**
 * Created by hungdev on 02/09/2018.
 */

public interface DiscoverContract {

    interface View extends BaseView<Presenter> {
        void showPlaylist(List<Playlist> playlists);
    }

    interface Presenter extends BasePresenter {
        void loadPlaylists();
    }
}
