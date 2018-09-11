package com.framgia.music_31.screens.player;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.data.source.local.RepeatSharedPreferences;
import com.framgia.music_31.data.source.local.ShuffleSharedPreferences;
import com.framgia.music_31.service.DownloadTrackService;
import com.framgia.music_31.service.MusicService;
import com.framgia.music_31.utils.Constants;
import com.squareup.picasso.Picasso;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class PlayerMusicActivity extends AppCompatActivity
        implements PlayerMusicContract.View, View.OnClickListener, SeekBar.OnSeekBarChangeListener,
        CompoundButton.OnCheckedChangeListener {

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
    private CheckBox mCheckBoxShuffle;
    private ImageView mImageViewRepeatController;
    private ImageView mImageViewDownload;
    private SeekBar mSeekBar;
    private TextView mTextViewCurrentTime;
    private TextView mTextViewTimeTotal;
    private PlayerMusicPresenter mPresenter;
    private MusicService mMusicService;
    private BroadcastReceiver mBroadcastReceiver;
    private Boolean mIsBound;
    private Handler mHandler = new Handler();
    private SharedPreferences.Editor mEditorShuffle;
    private SharedPreferences mPreferencesShuffle;
    private SharedPreferences.Editor mEditorRepeat;
    private SharedPreferences mPreferencesRepeat;
    private int mLevelImage;
    public static final int LEVEL_0 = 0;
    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = 2;

    @IntDef({LEVEL_0, LEVEL_1, LEVEL_2})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Level {}

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
        initBroadCast();
        super.onStart();
    }

    private void initBroadCast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MusicService.ACTION_SONG_CHANGED);
        intentFilter.addAction(MusicService.ACTION_STATUS_MEDIA_PLAYER);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case MusicService.ACTION_SONG_CHANGED:
                        updateSong(mMusicService.getCurrentSong());
                        break;
                    case MusicService.ACTION_STATUS_MEDIA_PLAYER:
                        changePlayerControl();
                        break;
                }
            }
        };
        registerReceiver(mBroadcastReceiver, intentFilter);
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
        mCheckBoxShuffle = findViewById(R.id.image_shuffle_controller);
        mImageViewRepeatController = findViewById(R.id.image_repeat_controller);
        mImageViewDownload = findViewById(R.id.image_download);
    }

    private void initData() {
        mPresenter = new PlayerMusicPresenter();
        mPresenter.setView(this);
        mPreferencesShuffle = ShuffleSharedPreferences.getSharedPreferences(this);
        mEditorShuffle = ShuffleSharedPreferences.getEditor(this);
        mPreferencesRepeat = RepeatSharedPreferences.getSharedPreferences(this);
        mEditorRepeat = RepeatSharedPreferences.getEditor(this);
        Song song = getIntent().getParcelableExtra(KEY_SONG);
        mTextViewTitle.setText(song.getSongName());
        mTextViewArtist.setText(song.getSingerName());
        mTextViewTimeTotal.setText(getFormatString(song.getDuration()));
        Picasso.with(this).load(song.getUrlImage()).into(mImageViewSong);
        mCheckBoxShuffle.setChecked(mPreferencesShuffle.getBoolean(Constants.KEY_SHUFFLE, false));
        mImageViewRepeatController.setImageLevel(mPreferencesRepeat.getInt(Constants.KEY_REPEAT, Constants.IMAGE_LEVEL_DEFAULT));
        mImageViewBackStack.setOnClickListener(this);
        mCheckBoxShuffle.setOnCheckedChangeListener(this);
        mImageViewPreviousController.setOnClickListener(this);
        mImageViewPlayerControl.setOnClickListener(this);
        mImageViewNextController.setOnClickListener(this);
        mImageViewRepeatController.setOnClickListener(this);
        mImageViewDownload.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    private String getFormatString(int time) {
        int minute = ((time / Constants.MILLIS) / Constants.SIXTY);
        int second = (time / Constants.MILLIS) % Constants.SIXTY;
        return String.format("%d:%02d", minute, second);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.PlayerBinder binder = (MusicService.PlayerBinder) service;
            mMusicService = binder.getService();
            mIsBound = true;
            updateTime();
            changePlayerControl();
            updateSong(mMusicService.getCurrentSong());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIsBound = false;
            mMusicService.unbindService(mServiceConnection);
            mHandler.removeCallbacks(mRunnable);
        }
    };

    @Override
    protected void onStop() {
        if (mIsBound) {
            unbindService(mServiceConnection);
            unregisterReceiver(mBroadcastReceiver);
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
                changeMediaPlayerStatus();
                break;
            case R.id.image_repeat_controller:
                changeImageLevel();
                break;
            case R.id.image_download:
                downloadTrack();
                break;
            case R.id.image_back_stack:
                finish();
                break;
        }
    }

    private void downloadTrack() {
        Song song = mMusicService.getCurrentSong();
        startService(DownloadTrackService.getIntentServiceDownload(this, song.getSongName(),
                song.getUrl()));
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
            mMusicService.updateProgress(progress);
            mSeekBar.setProgress(progress);
            mTextViewCurrentTime.setText(getFormatString(currentTime));
            mHandler.postDelayed(this, DELAY_MILLIS);
        }
    };

    private void changePlayerControl() {
        if (mMusicService.isPlaying()) {
            mImageViewPlayerControl.setImageResource(R.drawable.ic_pause_white_48dp);
        } else {
            mImageViewPlayerControl.setImageResource(R.drawable.ic_play_arrow_white_48dp);
        }
    }

    private void changeMediaPlayerStatus() {
        if (!mIsBound) {
            return;
        }
        if (mMusicService.isPlaying()) {
            mMusicService.pause();
        } else {
            mMusicService.start();
        }
    }

    private void changeImageLevel() {
        if(mLevelImage == LEVEL_2){
            mLevelImage = LEVEL_0;
        }else {
            mLevelImage++;
        }
        setRepeat(mLevelImage);
    }

    private void setRepeat(@Level int level) {
        mImageViewRepeatController.setImageLevel(level);
        mEditorRepeat.putInt(Constants.KEY_REPEAT, level);
        mEditorRepeat.apply();
    }

    private void setShuffle(boolean isChecked) {
        mEditorShuffle.putBoolean(Constants.KEY_SHUFFLE, isChecked);
        mEditorShuffle.apply();
        mMusicService.setShuffle(isChecked);
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.i("Shuffle", isChecked + "");
        setShuffle(isChecked);
    }
}
