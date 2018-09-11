package com.framgia.music_31.service;

import android.app.Notification;
import android.app.PendingIntent;
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
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.screens.player.PlayerMusicActivity;
import com.framgia.music_31.utils.Constants;
import com.framgia.music_31.utils.Utils;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicService extends Service
        implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {
    public static final String ACTION_NEXT = "com.framgia.music_31.ACTION_NEXT";
    public static final String ACTION_PREVIOUS = "com.framgia.music_31.ACTION_PREVIOUS";
    public static final String ACTION_COMPLETE = "com.framgia.music_31.ACTION_COMPLETE";
    public static final String ACTION_PLAY_CONTROL = "com.framgia.music_31.ACTION_PLAY_CONTROL";
    public static final String ACTION_CLEAR = "com.framgia.music_31.ACTION_CLEAR";
    public static final String ACTION_STATUS_MEDIA_PLAYER = "com.framgia.music_31.ACTION_STATUS_MEDIA_PLAYER";
    private static final String KEY_SONGS = "KEY_SONG";
    private static final String KEY_POSITON = "KEY_POSITON";
    private static final int DEFAULT_POSITON = 0;
    private static final int ONE = 1;
    private static final int ID_NOTI = 1;
    private static final int REQUEST_CODE_OK = 1;

    private int mPos;
    private List<Song> mSongs;
    private MediaPlayer mMediaPlayer;
    private RemoteViews mRemoteViews;
    private Intent mIntentPlayer;
    private PendingIntent mPendingIntent;
    private Notification mNotification;
    private final IBinder mIBinder = new PlayerBinder();

    public MusicService() {
    }

    public static Intent getIntentService(Context context, @Nullable List<Song> songs,
            @Nullable int position) {
        Intent intent = new Intent(context, MusicService.class);
        intent.putParcelableArrayListExtra(KEY_SONGS, (ArrayList<? extends Parcelable>) songs);
        intent.putExtra(KEY_POSITON, position);
        return intent;
    }

    public int getCurrentTime(){
        return mMediaPlayer.getCurrentPosition();
    }

    public Song getCurrentSong() {
        return mSongs.get(mPos);
    }

    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    @Override
    public void onCreate() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mPos = intent.getIntExtra(KEY_POSITON, DEFAULT_POSITON);
        mSongs = intent.getParcelableArrayListExtra(KEY_SONGS);
        playMusic(mPos);
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    private void playMusic(int postiton) {
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
            mPos = DEFAULT_POSITON;
        } else {
            mPos++;
        }
        mMediaPlayer.reset();
        playMusic(mPos);
    }

    public void previous() {
        if (mPos == DEFAULT_POSITON) {
            mPos = mSongs.size() - ONE;
        } else {
            mPos--;
        }
        mMediaPlayer.reset();
        playMusic(mPos);
    }

    public void seekTo(int progress) {
        int total = mSongs.get(mPos).getDuration();
        mMediaPlayer.seekTo( Utils.getCurrentTime(progress, total));
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mMediaPlayer.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Intent intent = new Intent(ACTION_COMPLETE);
        sendBroadcast(intent);
    }

    public class PlayerBinder extends Binder {

        public MusicService getService() {
            return MusicService.this;
        }
    }
}
