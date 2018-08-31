package com.framgia.music_31;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.framgia.music_31.Adapter.PlaylistAdapter;
import com.framgia.music_31.model.Playlist;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {

    private List<Playlist> mPlaylists;

    public static DiscoverFragment newInstance() {
        
        DiscoverFragment discoverFragment = new DiscoverFragment();
        return discoverFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        mPlaylists = new ArrayList<>();
        mPlaylists.add(new Playlist("Nhac Mr.Siro", R.drawable.mrsiro));
        mPlaylists.add(new Playlist("Nhac mua", R.drawable.danbo));
        mPlaylists.add(new Playlist("Nhac Mr.Siro", R.drawable.mrsiro));
        PlaylistAdapter adapter = new PlaylistAdapter(mPlaylists);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mLayoutManager);
        Toast.makeText(getActivity(), "AAAAAAAAAAA", Toast.LENGTH_SHORT).show();
        return view;
    }
}
