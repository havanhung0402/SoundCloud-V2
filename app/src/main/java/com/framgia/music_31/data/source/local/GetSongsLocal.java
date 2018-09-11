package com.framgia.music_31.data.source.local;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.data.source.CallBack;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungdev on 14/09/2018.
 */

public class GetSongsLocal extends AsyncTask<Void, Void, List<Song>>{

    private CallBack mCallBack;
    private List<Song> mSongs;
    private Context mContext;
    private int mTitleColIndex;
    private int mArtistColIndex;
    private int mPathIndex;
    private int mDurationIndex;

    public GetSongsLocal(Context context, CallBack callBack) {
        mContext = context;
        mCallBack = callBack;
    }

    private void outputSong(Cursor cursor) {
        String title = cursor.getString(mTitleColIndex);
        String artist = cursor.getString(mArtistColIndex);
        int duration = cursor.getInt(mDurationIndex);
        String path = cursor.getString(mPathIndex);
        mSongs.add(new Song(title, artist, duration, path));
    }

    private void inputCursor(Cursor cursor) {
        mTitleColIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);
        mArtistColIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        mDurationIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
        mPathIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
    }

    @Override
    protected List<Song> doInBackground(Void... voids) {
        mSongs = new ArrayList<>();
        ContentResolver contentResolver = mContext.getContentResolver();
        Uri baseUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(baseUri,null, null, null, null);
        if (cursor == null){
            return null;
        }else if (!cursor.moveToFirst()){
            return null;
        }else {
            inputCursor(cursor);
            while (cursor.moveToNext()){
                outputSong(cursor);
            }
        }
        return mSongs;
    }

    @Override
    protected void onPostExecute(List<Song> songs) {
        super.onPostExecute(songs);
        if (!songs.isEmpty()){
            mCallBack.onSusscess(songs);
        }
    }
}
