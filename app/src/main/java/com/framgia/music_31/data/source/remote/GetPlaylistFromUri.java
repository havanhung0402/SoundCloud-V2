package com.framgia.music_31.data.source.remote;

import android.os.AsyncTask;
import com.framgia.music_31.data.model.Playlist;
import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.data.source.CallBack;
import com.framgia.music_31.data.source.DiscoverDataSource;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by hungdev on 06/09/2018.
 */

public class GetPlaylistFromUri extends AsyncTask<String, Void, List<Playlist>> {

    private static final String COLLECTION = "collection";
    private static final String PLAYLISTS = "playlists";
    private static final int POSITION = 2;
    private Exception mException;
    private CallBack mCallback;

    public GetPlaylistFromUri(CallBack callback) {
        mCallback = callback;
    }

    @Override
    protected List<Playlist> doInBackground(String... strings) {
        List<Playlist> playlists = new ArrayList<>();
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            StringBuffer dataBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                dataBuffer.append(line + "\n");
            }
            String jsonData = dataBuffer.toString();
            getCollection(jsonData, playlists);
        } catch (Exception e) {
            mException = e;
        }
        return playlists;
    }

    @Override
    protected void onPostExecute(List<Playlist> playlists) {
        super.onPostExecute(playlists);
        if (mException != null) {
            mCallback.onError(mException);
        }
        if (!playlists.isEmpty()) {
            mCallback.onSusscess(playlists);
        } else {
            mCallback.onError(mException);
        }
    }

    private void addPlaylist(List<Playlist> playlists, JSONArray jsonArray) {
        JSONObject playlist = null;
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                playlist = jsonArray.getJSONObject(i);
                String title = playlist.getString(Song.JsonTrackKey.TITLE);
                String url_image = playlist.getString(Song.JsonTrackKey.ARTWORK_URL);
                playlists.add(new Playlist(title, url_image));
            } catch (Exception e) {
                mException = e;
            }
        }
    }

    private void getCollection(String jsonData, List<Playlist> playlists) {
        JSONObject jsonObject = null;
        JSONArray playlistsJson = null;
        try {
            jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray(COLLECTION);
            JSONObject collectionJson = jsonArray.getJSONObject(POSITION);
            playlistsJson = collectionJson.getJSONArray(PLAYLISTS);
            addPlaylist(playlists, playlistsJson);
        } catch (Exception e) {
            mException = e;
        }
    }
}
