package com.framgia.music_31.screens.mymusic;

import com.framgia.music_31.BasePresenter;
import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.screens.song.SongContract;
import java.util.List;

/**
 * Created by hungdev on 14/09/2018.
 */

public interface MyMusicContract {

    interface View {
        void onGetSongsSuccess(List<Song> songs);

        void onGetSongsError(Exception e);
    }

    interface Presenter extends BasePresenter<MyMusicContract.View> {
        void loadSongs();
    }
}
