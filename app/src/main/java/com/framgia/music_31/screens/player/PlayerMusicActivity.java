package com.framgia.music_31.screens.player;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Song;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class PlayerMusicActivity extends AppCompatActivity
        implements PlayerMusicContract.View, View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    public static final String KEY_SONG = "song";
    public static final String KEY_POSITON = "position";
    public static final String KEY_LIST_SONG = "songs";
    public static final int DEFAULT_POSITON = 0;

    private ImageView mImageViewBackStack;
    private TextView mTextViewTitle;
    private TextView mTextViewArtist;
    private ImageView mImageViewSong;
    private ImageView mImageViewPlayerControl;
    private ImageView mImageViewNextController;
    private ImageView mImageViewPreviousController;
    private ImageView mImageViewShuffleController;
    private ImageView mImageViewRepeatController;
    private SeekBar mSeekBar;
    private TextView mTextViewCurrentTime;
    private TextView mTextViewTimeTotal;
    private PlayerMusicPresenter mPresenter;

    public static Intent getPlayerIntent(Context context, Song song) {
        Intent intent = new Intent(context, PlayerMusicActivity.class);
        intent.putExtra(KEY_SONG, song);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initComponents();
        initData();
    }

    private void initComponents() {
        mImageViewBackStack = findViewById(R.id.image_back_stack);
        mTextViewTitle = findViewById(R.id.text_title);
        mTextViewArtist = findViewById(R.id.text_artist);
        mImageViewSong = findViewById(R.id.image_song);
        mSeekBar = findViewById(R.id.seekBar);
        mTextViewCurrentTime = findViewById(R.id.text_current_time);
        mTextViewTimeTotal = findViewById(R.id.text_time_total);
        mImageViewPlayerControl = findViewById(R.id.image_player_control);
        mImageViewNextController = findViewById(R.id.image_next_controller);
        mImageViewPreviousController = findViewById(R.id.image_previous_controller);
        mImageViewShuffleController = findViewById(R.id.image_shuffle_controller);
        mImageViewRepeatController = findViewById(R.id.image_repeat_controller);
    }

    private void initData() {
        mPresenter = new PlayerMusicPresenter();
        mPresenter.setView(this);
        Song song = getIntent().getParcelableExtra(KEY_SONG);
        mTextViewTitle.setText(song.getSongName());
        mTextViewArtist.setText(song.getSingerName());
        Picasso.with(this).load(song.getUrlImage()).into(mImageViewSong);
        mImageViewBackStack.setOnClickListener(this);
        mImageViewShuffleController.setOnClickListener(this);
        mImageViewPreviousController.setOnClickListener(this);
        mImageViewPlayerControl.setOnClickListener(this);
        mImageViewNextController.setOnClickListener(this);
        mImageViewRepeatController.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void updateSong(Song song) {

    }

    @Override
    public void updateSeekBar(int progress) {

    }

    @Override
    public void updateCurrentTime(String currentTime) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
