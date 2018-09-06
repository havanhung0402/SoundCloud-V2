package com.framgia.music_31.screens.song;

import com.framgia.music_31.BasePresenter;
import com.framgia.music_31.data.model.Song;
import java.util.List;

/**
 * Created by hungdev on 07/09/2018.
 */

public interface SongContract {

    interface View {
        void onGetSongsSuccess(List<Song> songs);

        void onGetSongsError(Exception e);
    }

    interface Presenter extends BasePresenter<View> {
        void loadSongs(String genreParam);
    }
}
