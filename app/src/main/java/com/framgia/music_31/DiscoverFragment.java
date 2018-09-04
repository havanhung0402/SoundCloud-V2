package com.framgia.music_31;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.music_31.adapter.ParentAdapter;
import com.framgia.music_31.model.Genre;
import com.framgia.music_31.model.Parent;
import com.framgia.music_31.model.Playlist;
import com.framgia.music_31.model.Song;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public static DiscoverFragment newInstance() {
        DiscoverFragment discoverFragment = new DiscoverFragment();
        return discoverFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
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
}
