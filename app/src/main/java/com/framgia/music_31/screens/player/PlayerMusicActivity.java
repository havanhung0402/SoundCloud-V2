package com.framgia.music_31.screens.player;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class PlayerMusicActivity extends AppCompatActivity
        implements PlayerMusicContract.View, View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    public static final String KEY_SONG = "song";
    public static final String KEY_POSITON = "position";
    public static final String KEY_LIST_SONG = "songs";
    public static final int DEFAULT_POSITON = 0;
    private static final int DELAY_MILLIS = 1000;

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
    private MusicService mMusicService;
    private Boolean mIsBound;
    private Handler mHandler = new Handler();

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

    @Override
    protected void onStart() {
        bindService(MusicService.getIntentService(this, null, DEFAULT_POSITON), mServiceConnection,
                BIND_AUTO_CREATE);
        super.onStart();
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
        mTextViewTimeTotal.setText(getFormatString(song.getDuration()));
        Picasso.with(this).load(song.getUrlImage()).into(mImageViewSong);
        mImageViewBackStack.setOnClickListener(this);
        mImageViewShuffleController.setOnClickListener(this);
        mImageViewPreviousController.setOnClickListener(this);
        mImageViewPlayerControl.setOnClickListener(this);
        mImageViewNextController.setOnClickListener(this);
        mImageViewRepeatController.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    private String getFormatString(int time){
        int minute = ((time/Constants.MILLIS) / Constants.SIXTY);
        int second = (time/Constants.MILLIS) % Constants.SIXTY;
        return String.format("%d:%02d", minute, second);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.PlayerBinder binder = (MusicService.PlayerBinder) service;
            mMusicService = binder.getService();
            updateTime();
            mIsBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIsBound = false;
            mMusicService.unbindService(mServiceConnection);
        }
    };

    @Override
    protected void onStop() {
        if (mIsBound) {
            unbindService(mServiceConnection);
            mIsBound = false;
        }
        super.onStop();
    }

    @Override
    public void updateSong(Song song) {
        mTextViewTitle.setText(song.getSongName());
        mTextViewArtist.setText(song.getSingerName());
        mTextViewTimeTotal.setText(getFormatString(song.getDuration()));
        Picasso.with(this).load(song.getUrlImage()).into(mImageViewSong);
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
                changeSuffle();
                break;
            case R.id.image_repeat_controller:
                changeRepeat();
                break;
            case R.id.image_back_stack:
                finish();
                break;
        }
    }

    private void updateTime() {
        mHandler.postDelayed(mRunnable, DELAY_MILLIS);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            int currentTime = mMusicService.getCurrentTime();
            int total = mMusicService.getCurrentSong().getDuration();
            int progress = (int) (((double) Constants.MAX_PROGRESS / total) * currentTime);
            mSeekBar.setProgress(progress);
            mTextViewCurrentTime.setText(getFormatString(currentTime));
            mHandler.postDelayed(this, DELAY_MILLIS);
        }
    };

    private void setPlayerControl() {
        if (!mIsBound) {
            return;
        }
        if (mMusicService.isPlaying()) {
            mMusicService.pause();
        } else {
            mMusicService.start();
        }
    }

    private void changeRepeat() {
    }

    private void changeSuffle() {
    }

    private void nextMusic() {
        if (mIsBound) {
            mMusicService.next();
            updateSong(mMusicService.getCurrentSong());
        }
    }

    private void previousMusic() {
        if (mIsBound) {
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
