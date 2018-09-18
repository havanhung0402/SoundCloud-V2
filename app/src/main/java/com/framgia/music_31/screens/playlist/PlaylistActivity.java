package com.framgia.music_31.screens.playlist;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Playlist;
import com.framgia.music_31.screens.song.SongActivity;
import java.util.ArrayList;
import java.util.List;

public class PlaylistActivity extends AppCompatActivity implements PlaylistAdapter.OnItemClick{

    public static final String KEY_PLAYLISTS = "key_playlists";
    private PlaylistAdapter mPlaylistAdapter;
    private RecyclerView mRecyclerView;

    public static Intent getPlaylistIntent(Context context, List<Playlist> playlists) {
        Intent intent = new Intent(context, PlaylistActivity.class);
        intent.putParcelableArrayListExtra(KEY_PLAYLISTS,
                (ArrayList<? extends Parcelable>) playlists);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = findViewById(R.id.recycler_view);
        List<Playlist> playlists = getIntent().getParcelableArrayListExtra(KEY_PLAYLISTS);
        mPlaylistAdapter = new PlaylistAdapter(playlists, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mPlaylistAdapter);
    }

    @Override
    public void onClick(Playlist playlist) {
        startActivity(SongActivity.getPlaylistIntent(this, playlist, "action.PLAYLIST"));
    }
}
