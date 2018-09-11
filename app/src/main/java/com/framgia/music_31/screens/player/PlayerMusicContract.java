package com.framgia.music_31.screens.player;

import com.framgia.music_31.BasePresenter;
import com.framgia.music_31.data.model.Song;

/**
 * Created by hungdev on 09/09/2018.
 */

public interface PlayerMusicContract {

    interface View {

        void updateSong(Song song);
    }

    interface Presenter extends BasePresenter<View>{

    }
}
