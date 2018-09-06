package com.framgia.music_31.service;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.screens.player.PlayerPresenter;
import com.framgia.music_31.utils.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicService extends Service
        implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {
    private static final String KEY_SONGS = "KEY_SONG";
    private static final String KEY_POSITON = "KEY_POSITON";
    private static final int DELAY_MILLIS = 1000;
    private static final int MILLIS = 1000;
    private static final int ONE_HUNDRED = 100;
    private static final int FIRTST = 0;
    private static final int SIXTY = 60;
    private static final int NINE = 60;
    private static final int ONE = 1;
    private static final String ZERO = "0";
    private static final String COLON = ":";

    private int mPos;
    private List<Song> mSongs;
    private PlayerPresenter mPresenter;
    private MediaPlayer mMediaPlayer;
    private RemoteViews mRemoteViews;
    private Notification mNotification;
    private Handler mHandler = new Handler();
    private OnCurrentTimeChange mCurrentTimeChange;
    private final IBinder mIBinder = new PlayerBinder();

    public MusicService() {
    }

    @Override
    public void onCreate() {
        mMediaPlayer = new MediaPlayer();
        mPresenter = new PlayerPresenter();
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_PREVIOUS);
        intentFilter.addAction(Constants.ACTION_PLAY_CONTROL);
        intentFilter.addAction(Constants.ACTION_NEXT);
        intentFilter.addAction(Constants.ACTION_CLEAR);
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
                    case Constants.ACTION_CLEAR:
                        break;
                    case Constants.ACTION_CURRENT_TIME_CHANGED:
                        break;
                }
            }
        };
        registerReceiver(broadcastReceiver, intentFilter);
        super.onCreate();
    }

    public static Intent getIntentService(Context context, @Nullable List<Song> songs,
            @Nullable int position) {
        Intent intent = new Intent(context, MusicService.class);
        intent.putParcelableArrayListExtra(KEY_SONGS, (ArrayList<? extends Parcelable>) songs);
        intent.putExtra(KEY_POSITON, position);
        return intent;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mPos = intent.getIntExtra(KEY_POSITON, FIRTST);
        mSongs = intent.getParcelableArrayListExtra(KEY_SONGS);
        playmusic(mPos);
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    private void playmusic(int postiton) {
        Song song = mSongs.get(postiton);
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(song.getUrl());
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }
    }

    public void start() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
        }
    }

    public void next() {
        if (mPos == mSongs.size() - ONE) {
            mPos = FIRTST;
            mMediaPlayer.reset();
            playmusic(mPos);
        } else {
            mPos++;
            mMediaPlayer.reset();
            playmusic(mPos);
        }
    }

    public void previous() {
        if (mPos == FIRTST) {
            mPos = mSongs.size() - ONE;
            mMediaPlayer.reset();
            playmusic(mPos);
        } else {
            mPos--;
            mMediaPlayer.reset();
            playmusic(mPos);
        }
    }

    private void updateTime() {
        mHandler.postDelayed(runnable, DELAY_MILLIS);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentTime = mMediaPlayer.getCurrentPosition();
            int total = mMediaPlayer.getDuration();
            int minute = ((currentTime / MILLIS) / SIXTY);
            int second = (currentTime / MILLIS) % SIXTY;
            String current_time;
            if (second > NINE) {
                current_time = ZERO + minute + COLON + second;
            } else {
                current_time = ZERO + minute + COLON + ZERO + second;
            }
            int progress = (int) (((double) ONE_HUNDRED / total) * currentTime);
            mCurrentTimeChange.OnSeekBarChanged(progress);
            mCurrentTimeChange.CurrentTimeChanged(current_time);
            mHandler.postDelayed(this, DELAY_MILLIS);
        }
    };

    public List<Song> getSongs() {
        return mSongs;
    }

    public Song getCurrentSong() {
        return mSongs.get(mPos);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Intent intent = new Intent(Constants.ACTION_COMPLETE);
        sendBroadcast(intent);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mMediaPlayer.start();
        updateTime();
    }

    public void seekTo(int progress) {
        long total = mMediaPlayer.getDuration();
        mMediaPlayer.seekTo((int) Constants.getCurrentTime(progress, total));
    }

    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    public class PlayerBinder extends Binder {

        public MusicService getService(OnCurrentTimeChange currentTimeChange) {
            mCurrentTimeChange = currentTimeChange;
            return MusicService.this;
        }
    }

    public interface OnCurrentTimeChange {
        void OnSeekBarChanged(int progress);

        void CurrentTimeChanged(String currentTime);
    }
}
