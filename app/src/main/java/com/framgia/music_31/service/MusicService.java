package com.framgia.music_31.service;

import android.app.Notification;
import android.app.NotificationManager;
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
import android.util.Log;
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
    public static final String ACTION_PLAY_CONTROL = "com.framgia.music_31.ACTION_PLAY_CONTROL";
    public static final String ACTION_CLEAR = "com.framgia.music_31.ACTION_CLEAR";
    public static final String ACTION_STATUS_MEDIA_PLAYER =
            "com.framgia.music_31.ACTION_STATUS_MEDIA_PLAYER";
    public static final String ACTION_SONG_CHANGED = "com.framgia.music_31.ACTION_SONG_CHANGED";
    private static final String KEY_SONGS = "KEY_SONG";
    private static final String KEY_POSITON = "KEY_POSITON";
    private static final String CHANNEL_ID = "1";
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
    private NotificationCompat.Builder mNotification;
    private NotificationManager mNotificationManager;
    private BroadcastReceiver mBroadcastReceiver;
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

    public int getCurrentTime() {
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
        initBroadCast();
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

    private void initBroadCast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_PREVIOUS);
        intentFilter.addAction(ACTION_PLAY_CONTROL);
        intentFilter.addAction(ACTION_NEXT);
        intentFilter.addAction(ACTION_CLEAR);
        intentFilter.addAction(ACTION_STATUS_MEDIA_PLAYER);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case ACTION_PREVIOUS:
                        previous();
                        break;
                    case ACTION_PLAY_CONTROL:
                        if (mMediaPlayer.isPlaying()) {
                            pause();
                        } else {
                            start();
                        }
                        break;
                    case ACTION_NEXT:
                        next();
                        break;
                    case ACTION_STATUS_MEDIA_PLAYER:
                        changePlayerControl();
                        break;
                    case ACTION_CLEAR:
                        stopForeground(true);
                        mMediaPlayer.reset();
                        stopSelf();
                        break;
                }
            }
        };
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    private void changePlayerControl() {
        if (mMediaPlayer.isPlaying()) {
            mRemoteViews.setImageViewResource(R.id.button_status_notify,
                    R.drawable.ic_pause_white_24dp);
            mNotificationManager.notify(ID_NOTI, mNotification.build());
        } else {
            mRemoteViews.setImageViewResource(R.id.button_status_notify,
                    R.drawable.ic_play_arrow_white_24dp);
            mNotificationManager.notify(ID_NOTI, mNotification.build());
        }
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
        showNotification();
    }

    private void showNotification() {
        Song song = mSongs.get(mPos);
        mIntentPlayer = new Intent(this, PlayerMusicActivity.class);
        mIntentPlayer.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mIntentPlayer.putExtra(PlayerMusicActivity.KEY_SONG, song);
        mPendingIntent = PendingIntent.getActivity(this, REQUEST_CODE_OK, mIntentPlayer,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews =
                new RemoteViews(getApplication().getPackageName(), R.layout.notification_media);

        //create notification
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotification =
                new NotificationCompat.Builder(getApplication(), CHANNEL_ID).setContentIntent(
                        mPendingIntent)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setCustomBigContentView(mRemoteViews)
                        .setCustomContentView(mRemoteViews)
                        .setContent(mRemoteViews);
        initRemoteViews(song);
        setListenerNotification();
        startForeground(ID_NOTI, mNotification.build());
    }

    private void setListenerNotification() {
        Intent intentPlay = new Intent(ACTION_PLAY_CONTROL);
        Intent intentPrevious = new Intent(ACTION_PREVIOUS);
        Intent intentNext = new Intent(ACTION_NEXT);
        Intent intentClear = new Intent(ACTION_CLEAR);
        PendingIntent pendingStatus =
                PendingIntent.getBroadcast(getApplication(), REQUEST_CODE_OK, intentPlay,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingPrevious =
                PendingIntent.getBroadcast(getApplication(), REQUEST_CODE_OK, intentPrevious,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingNext =
                PendingIntent.getBroadcast(getApplication(), REQUEST_CODE_OK, intentNext,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingClear =
                PendingIntent.getBroadcast(getApplication(), REQUEST_CODE_OK, intentClear,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.button_next_notify, pendingNext);
        mRemoteViews.setOnClickPendingIntent(R.id.button_previous_notify, pendingPrevious);
        mRemoteViews.setOnClickPendingIntent(R.id.button_status_notify, pendingStatus);
        mRemoteViews.setOnClickPendingIntent(R.id.image_clear_notify, pendingClear);
    }

    private void initRemoteViews(Song song) {
        String songTitle = song.getSongName();
        String artist = song.getSingerName();
        String image = song.getUrlImage();
        mRemoteViews.setTextViewText(R.id.text_song, songTitle);
        mRemoteViews.setTextViewText(R.id.text_artist, artist);
        if (image !=null){
            Picasso.with(getApplication())
                    .load(image)
                    .into(mRemoteViews, R.id.image_notification, ID_NOTI, mNotification.build());
        }else {
            mRemoteViews.setImageViewResource(R.id.image_notification, R.mipmap.ic_launcher);
        }

    }

    public void updateProgress(int progress) {
        mRemoteViews.setProgressBar(R.id.progress_notify, Constants.MAX_PROGRESS, progress, false);
        startForeground(ID_NOTI, mNotification.build());
    }

    public void pause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
            sendBroadcast(new Intent(ACTION_STATUS_MEDIA_PLAYER));
        }
    }

    public void start() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
            sendBroadcast(new Intent(ACTION_STATUS_MEDIA_PLAYER));
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
        mMediaPlayer.seekTo(Utils.getCurrentTime(progress, total));
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mMediaPlayer.start();
        sendBroadcast(new Intent(ACTION_STATUS_MEDIA_PLAYER));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Intent intent = new Intent(ACTION_NEXT);
        sendBroadcast(intent);
    }

    public class PlayerBinder extends Binder {

        public MusicService getService() {
            return MusicService.this;
        }
    }
}
