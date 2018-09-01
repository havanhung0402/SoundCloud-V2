package com.framgia.music_31.screen.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Genre;
import com.framgia.music_31.data.model.Playlist;
import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.data.model.Parent;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment implements DiscoverContract.View{

    private RecyclerView mRecyclerView;
    private DiscoverContract.Presenter mPresenter;

    public static DiscoverFragment newInstance() {
        DiscoverFragment discoverFragment = new DiscoverFragment();
        return discoverFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        List<Parent> mParents = new ArrayList<>();
        List<Playlist> mPlaylists = new ArrayList<>();
        List<Song> mGenres = new ArrayList<>();
        List<Genre>mSongs = new ArrayList<>();
        setLayout();
        setAdapter(mParents);
    }

    private void setAdapter(List<Parent> parents) {
        ParentAdapter parentAdapter = new ParentAdapter(parents);
        mRecyclerView.setAdapter(parentAdapter);
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
    }

    private void setLayout(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void setPresenter(DiscoverContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        Log.d("onResume", mPresenter+"");
    }

    @Override
    public void showPlaylist(List<Playlist> playlists) {

    }

}
