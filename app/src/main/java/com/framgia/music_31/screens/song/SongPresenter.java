package com.framgia.music_31.screens.song;

import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.data.repository.SongRepository;
import com.framgia.music_31.data.source.CallBack;
import java.util.List;

/**
 * Created by hungdev on 07/09/2018.
 */

public class SongPresenter implements SongContract.Presenter{
    private SongRepository mSongRepository;
    private SongContract.View mView;
    private String mGenre;

    public SongPresenter(SongRepository songRepository, String genreParam) {
        mSongRepository = songRepository;
        mGenre = genreParam;
    }

    @Override
    public void start() {

    }

    @Override
    public void setView(SongContract.View view) {
        mView = view;
    }

    @Override
    public void loadSongs(String genreParam) {
        mSongRepository.getSongs(genreParam,new CallBack<List<Song>>() {
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
