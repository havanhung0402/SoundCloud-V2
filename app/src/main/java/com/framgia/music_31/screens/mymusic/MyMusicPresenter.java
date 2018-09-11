package com.framgia.music_31.screens.mymusic;

import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.data.repository.SongRepository;
import com.framgia.music_31.data.source.CallBack;
import com.framgia.music_31.screens.song.SongContract;
import java.util.List;

/**
 * Created by hungdev on 14/09/2018.
 */

public class MyMusicPresenter implements MyMusicContract.Presenter{

    private SongRepository mSongRepository;

    public MyMusicPresenter(SongRepository songRepository) {
        mSongRepository = songRepository;
    }

    private MyMusicContract.View mView;

    @Override
    public void start() {

    }

    @Override
    public void setView(MyMusicContract.View view) {
        mView = view;
    }

    @Override
    public void loadSongs() {
        mSongRepository.getSongsLocal(new CallBack<List<Song>>() {
            @Override
            public void onSusscess(List<Song> datas) {
                mView.onGetSongsSuccess(datas);
            }

            @Override
            public void onError(Exception e) {
                mView.onGetSongsError(e);
            }
        });
    }
}
