package com.framgia.music_31.screens.mymusic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Parent;
import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.data.repository.SongRepository;
import com.framgia.music_31.data.source.local.SongLocalDataSource;
import com.framgia.music_31.data.source.remote.SongRemoteDataSource;
import com.framgia.music_31.screens.discover.ParentAdapter;
import com.framgia.music_31.screens.player.PlayerMusicActivity;
import com.framgia.music_31.service.MusicService;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyMusicFragment extends Fragment
        implements MyMusicContract.View, MyMusicAdapter.SongItemClickListener {
    private RecyclerView mRecyclerView;
    private MyMusicAdapter mAdapter;
    private MyMusicPresenter mPresenter;

    public static MyMusicFragment newInstance() {
        MyMusicFragment myMusicFragment = new MyMusicFragment();
        return myMusicFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_muisc, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        mPresenter = new MyMusicPresenter(
                SongRepository.getsInstance(SongRemoteDataSource.getInstance(),
                        SongLocalDataSource.getInstance(getContext())));
        mPresenter.setView(this);
        setLayout();
        setAdapter();
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
    }

    private void setLayout() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void setAdapter() {
        mAdapter = new MyMusicAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.loadSongs();
    }

    @Override
    public void onGetSongsSuccess(List<Song> songs) {
        mAdapter.addDataSong(songs);
    }

    @Override
    public void onGetSongsError(Exception e) {

    }

    @Override
    public void onSongClick(Song song, int positon, List<Song> songs) {
        startActivity(PlayerMusicActivity.getPlayerIntent(getActivity(), song));
        getActivity().startService(MusicService.getIntentService(getActivity(), songs, positon));
    }
}
