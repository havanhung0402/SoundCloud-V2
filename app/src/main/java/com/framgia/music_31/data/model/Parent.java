package com.framgia.music_31.data.model;

import android.support.annotation.StringDef;
import android.support.annotation.StringRes;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by hungdev on 31/08/2018.
 */

public class Parent<T> {
    public static final String PLAY_LIST = "PLAYLISTS";
    public static final String GENRES = "GENRES";
    public static final String CHART= "CHART";

    @StringDef({PLAY_LIST, GENRES, CHART})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Title {}

    private String mTitle;

    public Parent() {
    }

    public Parent(@Title String title) {
        mTitle = title;
    }

    @Title
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(@Title String title) {
        mTitle = title;
    }
}
