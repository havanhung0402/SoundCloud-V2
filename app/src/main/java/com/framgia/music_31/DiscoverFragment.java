package com.framgia.music_31;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.music_31.adapter.ParentAdapter;
import com.framgia.music_31.model.Playlist;
import com.framgia.music_31.model.ParentList;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {

    private List<ParentList> mParentLists;
    private List<Playlist> mPlaylists;
    private List<Song> mSongs;

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
        mSongs = new ArrayList<>();
        mSongs.add(new Song(1, "Tự lau nước mắt", "Mr.Siro",
                "http//:", "http//:"));
        mSongs.add(new Song(1, "Đã từng vô giá", "Mr.Siro",
                "http//:", "http//:"));
        mSongs.add(new Song(1, "Chỉ còn 1 người để yêu trên thế gian", "Mr.Siro",
                "http//:", "http//:"));
        mSongs.add(new Song(1, "Day dứt lỗi đau", "Mr.Siro",
                "http//:", "http//:"));
        mSongs.add(new Song(1, "Lặng lẽ tổn thương", "Mr.Siro",
                "http//:", "http//:"));
        mSongs.add(new Song(1, "Tiếng sét trong anh", "Mr.Siro",
                "http//:", "http//:"));
        mSongs.add(new Song(1, "Sống trong nỗi nhớ", "Mr.Siro",
                "http//:", "http//:"));
        mPlaylists.add(new Playlist("aaaaa", R.drawable.mrsiro));
        mPlaylists.add(new Playlist("Những bài hát việt có thể hit của Mr.Siro", R.drawable.mrsiro));
        mPlaylists.add(new Playlist("aaaaa", R.drawable.mrsiro));
        mParentLists = new ArrayList<>();
        mParentLists.add(new ParentList("PLAYLISTS", mPlaylists ));
        mParentLists.add(new ParentList("GENRES", mPlaylists));
        mParentLists.add(new ParentList("SONGS", mSongs));
        ParentAdapter superPLaylistAdapter = new ParentAdapter(mParentLists);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(superPLaylistAdapter);

        return view;
    }
}
