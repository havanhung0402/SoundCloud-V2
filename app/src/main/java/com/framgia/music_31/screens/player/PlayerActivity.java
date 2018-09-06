package com.framgia.music_31.screens.player;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.service.MusicService;
import com.framgia.music_31.utils.Constants;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity
        implements PlayerContract.View, View.OnClickListener, SeekBar.OnSeekBarChangeListener {

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
    private PlayerPresenter mPresenter;
    private MusicService mMusicService;
    private Boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initComponents();
        initData();
        initBroadCast();
    }

    private void initBroadCast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_PREVIOUS);
        intentFilter.addAction(Constants.ACTION_PLAY_CONTROL);
        intentFilter.addAction(Constants.ACTION_NEXT);
        intentFilter.addAction(Constants.ACTION_COMPLETE);
        intentFilter.addAction(Constants.ACTION_CURRENT_TIME_CHANGED);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case Constants.ACTION_PREVIOUS:
                        break;
                    case Constants.ACTION_PLAY_CONTROL:
                        break;
                    case Constants.ACTION_NEXT:
                        break;
                    case Constants.ACTION_COMPLETE:
                        nextMusic();
                        break;
                    case Constants.ACTION_CLEAR:
                        break;
                    case Constants.ACTION_CURRENT_TIME_CHANGED:
                        break;
                }
            }
        };
        registerReceiver(broadcastReceiver, intentFilter);
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
        mPresenter = new PlayerPresenter();
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
        bindService(MusicService.getIntentService(this, null, DEFAULT_POSITON), mServiceConnection,
                BIND_AUTO_CREATE);
    }

    public static Intent getPlayerIntent(Context context, Song song, int positon,
            List<Song> songs) {
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra(KEY_SONG, song);
        intent.putExtra(KEY_POSITON, positon);
        intent.putParcelableArrayListExtra(KEY_LIST_SONG, (ArrayList<? extends Parcelable>) songs);
        return intent;
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.PlayerBinder binder = (MusicService.PlayerBinder) service;
            mMusicService = binder.getService(new MusicService.OnCurrentTimeChange() {
                @Override
                public void OnSeekBarChanged(int progress) {
                    mSeekBar.setProgress(progress);
                }

                @Override
                public void CurrentTimeChanged(String currentTime) {
                    mTextViewCurrentTime.setText(currentTime);
                }
            });
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
            mMusicService.unbindService(mServiceConnection);
        }
    };

    @Override
    protected void onStop() {
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
        super.onStop();
    }

    @Override
    public void updateSong(Song song) {
        mTextViewTitle.setText(song.getSongName());
        mTextViewArtist.setText(song.getSingerName());
        Picasso.with(this).load(song.getUrlImage()).into(mImageViewSong);
    }

    @Override
    public void updateSeekBar(int progress) {
        mSeekBar.setProgress(progress);
    }

    @Override
    public void updateCurrentTime(String currentTime) {
        mTextViewCurrentTime.setText(currentTime);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_previous_controller:
                previousMusic();
                break;
            case R.id.image_next_controller:
                nextMusic();
                break;
            case R.id.image_player_control:
                setPlayerControl();
                break;
            case R.id.image_shuffle_controller:
                setShuffle();
                break;
            case R.id.image_repeat_controller:
                setRepeat();
                break;
        }
    }

    private void setPlayerControl() {
        if (mBound == true) {
            if (mMusicService.isPlaying()) {
                mImageViewPlayerControl.setImageResource(R.drawable.ic_play_arrow_white_48dp);
                mMusicService.pause();
            } else {
                mImageViewPlayerControl.setImageResource(R.drawable.ic_pause_white_48dp);
                mMusicService.start();
            }
        }
    }

    private void setRepeat() {
    }

    private void setShuffle() {
    }

    private void nextMusic() {
        if (mBound == true) {
            mMusicService.next();
            updateSong(mMusicService.getCurrentSong());
        }
    }

    private void previousMusic() {
        if (mBound == true) {
            mMusicService.previous();
            updateSong(mMusicService.getCurrentSong());
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            mMusicService.seekTo(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
