package com.framgia.music_31.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;
import com.framgia.music_31.R;
import com.framgia.music_31.data.source.CallBack;
import com.framgia.music_31.utils.Constants;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadTrackService extends IntentService {
    private static final int DEFAULT_POSITION = 0;
    private static final int DEFAULT_LENGTH = 0;
    private static final int NOTIFY_ID = 2;
    private static final int NOTIFY_ID_DOWNLOAD_SUCCESS = 3;
    private static final int ONE = 1;
    private static final int LENGTH_BYTE = 1024;
    private static final String NAME = "DownloadTrackService";
    private static final String EXTENSION = ".mp3";
    private static final String CHANNEL_ID = "2";
    private static final String ACTION_DOWNLOAD_TRACK = "com.framgia.music_31.service.action.FOO";
    private static final String EXTRA_URL = "com.framgia.music_31.service.extra.URL";
    private static final String EXTRA_TITLE = "com.framgia.music_31.service.extra.TITLE";
    private NotificationCompat.Builder mNotificationCompat;
    private NotificationManager mNotificationManager;

    public static Intent getIntentServiceDownload(Context context, String title, String url) {
        Intent intent = new Intent(context, DownloadTrackService.class);
        intent.setAction(ACTION_DOWNLOAD_TRACK);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_URL, url);
        context.startService(intent);
        return intent;
    }

    public DownloadTrackService() {
        super(NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (ACTION_DOWNLOAD_TRACK.equals(action)) {
                String url = intent.getStringExtra(EXTRA_URL);
                String title = intent.getStringExtra(EXTRA_TITLE);
                handleActionDownload(url, title);
            }
        }
    }

    private void handleActionDownload(String url, String title) {
        int count;
        mNotificationCompat = new NotificationCompat.Builder(getApplication(), CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(getApplication().getString((R.string.text_downloading)))
                .setSmallIcon(R.drawable.ic_arrow_downward_white_24dp);
        mNotificationManager =
                (NotificationManager) getApplication().getSystemService(Context.NOTIFICATION_SERVICE);
        try {
            URL urlTrack = new URL(url);
            URLConnection connection = urlTrack.openConnection();
            connection.connect();
            int lengthOfFile = connection.getContentLength();
            File folder = new File(Constants.STORAGE_URL);
            if (!folder.exists()){
                folder.mkdirs();
            }
            InputStream input = new BufferedInputStream(urlTrack.openStream());
            OutputStream output = new FileOutputStream(Constants.STORAGE_URL + title + EXTENSION);

            byte data[] = new byte[LENGTH_BYTE];

            long total = DEFAULT_LENGTH;

            while ((count = input.read(data)) != - ONE) {
                total += count;
                int progress = (int) (total * Constants.MAX_PROGRESS / lengthOfFile);
                updateProgressNotification(progress);
                output.write(data, DEFAULT_POSITION, count);
                Toast.makeText(getApplication(), title, Toast.LENGTH_SHORT).show();
            }
            setDownloadSuccess(title);
            output.flush();
            output.close();
            input.close();
        } catch (Exception e) {

        }
    }

    private void setDownloadSuccess(String title) {
        mNotificationManager.cancel(NOTIFY_ID);
        mNotificationCompat = new NotificationCompat.Builder(getApplication(), CHANNEL_ID);
        mNotificationCompat
                .setContentTitle(title)
                .setContentText(getApplication().getString(R.string.text_download_success))
                .setSmallIcon(R.drawable.ic_arrow_downward_white_24dp);
        mNotificationManager.notify(NOTIFY_ID_DOWNLOAD_SUCCESS, mNotificationCompat.build());
    }

    private void updateProgressNotification(int progress) {
        mNotificationCompat.setProgress(Constants.MAX_PROGRESS, progress, false);
        mNotificationManager.notify(NOTIFY_ID, mNotificationCompat.build());
    }
}
