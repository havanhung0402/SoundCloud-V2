package com.framgia.music_31.data.model;

/**
 * Created by hungdev on 31/08/2018.
 */

public class Playlist {

    private String mTitle;
    private String mUrlImage;

    public Playlist() {
    }

    public Playlist(String title, String urlImage) {
        mTitle = title;
        mUrlImage = urlImage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrlImage() {
        return mUrlImage;
    }

    public void setUrlImage(String urlImage) {
        mUrlImage = urlImage;
    }

    public static class JsonPlayistKey {
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String URI = "uri";
        public static final String ARTWORK_URL = "artwork_url";
    }
}
