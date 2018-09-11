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
import android.widget.RemoteViews;
import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.screens.player.PlayerMusicPresenter;
import com.framgia.music_31.utils.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicService extends Service
        implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {
    private static final String KEY_SONGS = "KEY_SONG";
    private static final String KEY_POSITON = "KEY_POSITON";
    private static final int FIRTST = 0;
    private static final int ONE = 1;

    private int mPos;
    private List<Song> mSongs;
    private MediaPlayer mMediaPlayer;

    public MusicService() {
    }

    @Override
    public void onCreate() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnPreparedListener(this);
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
        return null;
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

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mMediaPlayer.start();
    }
}
