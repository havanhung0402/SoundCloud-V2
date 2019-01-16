package com.framgia.music_31.screens.song;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Genre;
import com.framgia.music_31.data.model.Playlist;
import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.data.repository.SongRepository;
import com.framgia.music_31.data.source.local.SongLocalDataSource;
import com.framgia.music_31.data.source.remote.SongRemoteDataSource;
import com.framgia.music_31.screens.BaseActivity;
import com.framgia.music_31.screens.player.PlayerMusicActivity;
import com.framgia.music_31.service.MusicService;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class SongActivity extends AppCompatActivity implements BaseActivity, SongContract.View,
        SongAdapter.SongItemClickListener{

    private static final String KEY_GENRE = "KEY_GENRE";
    private static final int IMAGE_DEFAULT = R.drawable.ic_ambient;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private RecyclerView mRecyclerView;
    private ImageView mImagePlaylist;
    private SongContract.Presenter mPresenter;
    private SongAdapter mSongAdapter;

    public static <T>Intent getPlaylistIntent(Context context, T genre, String action) {
        Intent intent = new Intent(context, SongActivity.class);
        intent.setAction(action);
        intent.putExtra(KEY_GENRE, (Parcelable) genre);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initComponents();
        initData();
    }

    public void initComponents() {
        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        mImagePlaylist = findViewById(R.id.image_genre);
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    public void initData() {
        String url = null;
        Genre genre = null;
        String action;
        Playlist playlist;
        if ((action = getIntent().getAction()).equals("action.GENRE")){
            genre = getIntent().getParcelableExtra(KEY_GENRE);
            url = genre.getUrl();
            mCollapsingToolbarLayout.setTitle(genre.getTitle());
            mImagePlaylist.setImageResource(genre.getImage());
        }else if ((action = getIntent().getAction()).equals("action.PLAYLIST")){
            playlist = getIntent().getParcelableExtra(KEY_GENRE);
            url = playlist.getUrl();
            Log.i("Url:", url);
            mCollapsingToolbarLayout.setTitle(playlist.getTitle());
            Picasso.with(this).load(playlist.getUrlImage()).into(mImagePlaylist);
        }
        mPresenter = new SongPresenter(SongRepository.getsInstance(SongRemoteDataSource.getInstance(),
                SongLocalDataSource.getInstance(this)));
        mPresenter.setView(this);
        List<Song> songs = new ArrayList<>();
        mSongAdapter = new SongAdapter(songs, this);
        initView(url, action);
    }

    public void initView(String url, String action) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mSongAdapter);
        mPresenter.loadSongs(url, action);
    }

    @Override
    public void onGetSongsSuccess(List<Song> songs) {
        mSongAdapter.addDataSong(songs);
    }

    @Override
    public void onGetSongsError(Exception e) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSongClick(Song song, int positon, List<Song> songs) {
        startActivity(PlayerMusicActivity.getPlayerIntent(this, song));
        startService(MusicService.getIntentService(this, songs, positon));
    }
}
