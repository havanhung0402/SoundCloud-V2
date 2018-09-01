package com.framgia.music_31.data.model;

/**
 * Created by hungdev on 01/09/2018.
 */

public class Genre {
    private String mTitle;
    private int mImage;

    public Genre() {
    }

    public Genre(String title, int image) {
        mTitle = title;
        mImage = image;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }
}
