package com.framgia.music_31.screens.discover;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Genre;
import com.framgia.music_31.data.model.Playlist;
import com.framgia.music_31.data.model.Parent;
import com.framgia.music_31.data.repository.DiscoverRepository;
import com.framgia.music_31.data.source.local.DiscoverLocalDataSource;
import com.framgia.music_31.data.source.remote.DiscoverRemoteDataSource;
import com.framgia.music_31.screens.playlist.PlaylistActivity;
import com.framgia.music_31.screens.song.SongActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment
        implements DiscoverContract.View, GenreAdapter.OnGenreItemClickListener, PlaylistAdapter.OnItemPlaylistClickListener,

        ParentAdapter.OnViewMoreClickListener{

    private static final String KEY_PLAYLIST_ARGUMENT = "playlist";
    private RecyclerView mRecyclerView;
    private DiscoverContract.Presenter mPresenter;
    private ParentAdapter mParentAdapter;

    public static DiscoverFragment newInstance(List<Playlist> playlists) {
        DiscoverFragment discoverFragment = new DiscoverFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(KEY_PLAYLIST_ARGUMENT, (ArrayList<? extends Parcelable>) playlists);
        discoverFragment.setArguments(args);
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
        mPresenter = new DiscoverPresenter(
                DiscoverRepository.getInstance(DiscoverRemoteDataSource.getInstance(),
                        DiscoverLocalDataSource.getInstance()));
        mPresenter.setView(this);
        List<Parent> parents = new ArrayList<>();
        parents.add(new Parent(Parent.PLAY_LIST));
        parents.add(new Parent(Parent.GENRES));
        setLayout();
        setAdapter(parents);
        mPresenter.start();
    }

    private void setAdapter(List<Parent> parents) {
        mParentAdapter = new ParentAdapter(this,this, parents, this);
        mRecyclerView.setAdapter(mParentAdapter);
        if (getArguments() != null) {
            List<Playlist> playlists = getArguments().getParcelableArrayList(KEY_PLAYLIST_ARGUMENT);
            mParentAdapter.addData(playlists);
        }

    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
    }

    private void setLayout() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onGetDataError(Exception e) {

    }

    @Override
    public void onGetGenreSuccess(List<Genre> genres) {
        mParentAdapter.addData(genres);
    }

    @Override
    public void onGenreClick(Genre genre) {
        startActivity(SongActivity.getPlaylistIntent(getActivity(), genre, "action.GENRE"));
    }

    @Override
    public void onItemClick(Playlist playlist) {
        startActivity(SongActivity.getPlaylistIntent(getActivity(), playlist, "action.PLAYLIST"));
    }

    @Override
    public void onViewMoreClick(List<Playlist> playlists) {
        startActivity(PlaylistActivity.getPlaylistIntent(getActivity(), playlists));
    }
}
