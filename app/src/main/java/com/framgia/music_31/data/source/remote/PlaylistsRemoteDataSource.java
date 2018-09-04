package com.framgia.music_31.data.source.remote;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.framgia.music_31.data.model.Playlist;
import com.framgia.music_31.data.source.PlaylistsDataSource;
import com.framgia.music_31.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hungdev on 02/09/2018.
 */

public class PlaylistsRemoteDataSource implements PlaylistsDataSource {
    private static PlaylistsRemoteDataSource INSTANCE = null;
    private RequestQueue mRequestQueue;

    public PlaylistsRemoteDataSource(Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static PlaylistsRemoteDataSource getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = new PlaylistsRemoteDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getPlaylists(final LoadPlaylistsCallback callback) {
        String url = Constants.SoundCloud.BASE_URL + Constants.SoundCloud.SELECTION
                + Constants.SoundCloud.CLIENT_ID;
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url, null, new
                Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("collection");
                    JSONObject collectionJson = jsonArray.getJSONObject(2);
                    String titleParent = collectionJson.getString("title");
                    JSONArray playlistsJson = collectionJson.getJSONArray("playlists");
                    List<Playlist> playlists = new ArrayList<>();
                    for (int i = 0; i < playlistsJson.length(); i++){
                        JSONObject playlist = playlistsJson.getJSONObject(i);
                        String title = playlist.getString("title");
                        String url_image = playlist.getString("artwork_url");
                        playlists.add(new Playlist(title, url_image));
                        Log.d("title", title);
                        Log.d("url_image", url_image);
                    }
                    if (!playlists.isEmpty()){
                        callback.onPlaylistsLoaded(playlists);
                    }else {
                        callback.onDataNotAvailable();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }
}
