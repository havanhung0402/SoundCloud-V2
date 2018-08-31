package com.framgia.music_31.model;

/**
 * Created by hungdev on 31/08/2018.
 */

public class Playlist {

    private String mTitle;
    private int mUrl_image;

    public Playlist() {
    }

    public Playlist(String title, int url_image) {
        mTitle = title;
        mUrl_image = url_image;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getUrl_image() {
        return mUrl_image;
    }

    public void setUrl_image(int url_image) {
        mUrl_image = url_image;
    }
}
