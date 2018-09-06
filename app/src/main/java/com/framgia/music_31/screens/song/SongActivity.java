package com.framgia.music_31.screens.song;

import android.content.Context;
import android.content.Intent;
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
import com.framgia.music_31.screens.discover.DiscoverFragment;
import java.util.ArrayList;
import java.util.List;

public class SongActivity extends AppCompatActivity implements SongContract.View{

    private static final String KEY_GENRE = "KEY_GENRE";
    private static final int IMAGE_DEFAULT = R.drawable.ic_ambient;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private RecyclerView mRecyclerView;
    private ImageView mImagePlaylist;
    private SongContract.Presenter mPresenter;
    private SongAdapter mSongAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initComponents();
        initData();
    }

    public static Intent getPlaylistIntent(Context context, Genre genre) {
        Intent intent = new Intent(context, SongActivity.class);
        intent.putExtra(KEY_GENRE, genre);
        return intent;
    }

    private void initComponents() {
        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        mImagePlaylist = findViewById(R.id.image_genre);
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    private void initData() {
        Genre genre = getIntent().getParcelableExtra(KEY_GENRE);
        String genreParam = genre.getParamGenre();
        mCollapsingToolbarLayout.setTitle(genre.getTitle());
        mImagePlaylist.setImageResource(genre.getImage());
        mPresenter = new SongPresenter(SongRepository.getsInstance(SongRemoteDataSource.getInstance(),
                SongLocalDataSource.getInstance()), genre.getParamGenre());
        mPresenter.setView(this);
        List<Song> songs = new ArrayList<>();
        mSongAdapter = new SongAdapter(songs);
        initView(genreParam);
    }

    private void initView(String genreParam) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mSongAdapter);
        mPresenter.loadSongs(genreParam);
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
}
