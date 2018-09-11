package com.framgia.music_31.data.source.remote;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.framgia.music_31.BuildConfig;
import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.data.source.CallBack;
import com.framgia.music_31.utils.Constants;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hungdev on 07/09/2018.
 */

public class GetSongsFromUri extends AsyncTask<String, Void, List<Song>> {

    private static final String KEY_COLLECTION = "collection";
    private static final String KEY_USER = "user";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_TRACK = "track";

    private Exception mException;
    private CallBack mCallback;

    public GetSongsFromUri(CallBack callback) {
        mCallback = callback;
    }

    @Override
    protected List<Song> doInBackground(String... strings) {
        List<Song> songs = new ArrayList<>();
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            StringBuffer dataBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                dataBuffer.append(line + Constants.SLASH_N);
            }
            String jsonData = dataBuffer.toString();
            getCollection(jsonData, songs);
        } catch (Exception e) {
            mException = e;
        }
        return songs;
    }

    private void getCollection(String jsonData, List<Song> songs) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray(KEY_COLLECTION);
            addSong(jsonArray, songs);
        } catch (Exception e) {
            mException = e;
        }
    }

    private void addSong(JSONArray jsonArray, List<Song> songs) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject track = jsonObject.getJSONObject(KEY_TRACK);
                String uri_image = track.getString(Song.JsonTrackKey.ARTWORK_URL);
                long id = track.getLong(Song.JsonTrackKey.ID);
                int duration = track.getInt(Song.JsonTrackKey.DURATION);
                String title = track.getString(Song.JsonTrackKey.TITLE);
                String artist = track.getJSONObject(KEY_USER).getString(KEY_FULL_NAME);
                String uri_stream = Constants.BASE_URL
                        + Constants.TRACKS
                        + Constants.SLASH
                        + id
                        + Constants.SLASH
                        + Constants.STREAM
                        + Constants.QUESTION_MARK
                        + Constants.CLIENT_ID
                        + BuildConfig.YOUR_API_KEY;
                songs.add(new Song(id, title, artist, uri_image, duration, uri_stream));
            } catch (JSONException e) {
                mException = e;
            }
        }
    }

    @Override
    protected void onPostExecute(List<Song> songs) {
        super.onPostExecute(songs);
        if (mException != null) {
            mCallback.onError(mException);
        }
        if (!songs.isEmpty()) {
            mCallback.onSusscess(songs);
        } else {
            mCallback.onError(mException);
        }
    }
}
