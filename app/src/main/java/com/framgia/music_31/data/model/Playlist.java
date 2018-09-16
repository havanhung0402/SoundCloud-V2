package com.framgia.music_31.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hungdev on 31/08/2018.
 */

public class Playlist implements Parcelable {

    private String mTitle;
    private String mUrlImage;

    public Playlist() {
    }

    public Playlist(String title, String urlImage) {
        mTitle = title;
        mUrlImage = urlImage;
    }

    protected Playlist(Parcel in) {
        mTitle = in.readString();
        mUrlImage = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mUrlImage);
    }

    public static final Creator<Playlist> CREATOR = new Creator<Playlist>() {
        @Override
        public Playlist createFromParcel(Parcel source) {
            return new Playlist(source);
        }

        @Override
        public Playlist[] newArray(int size) {
            return new Playlist[size];
        }
    };

    public static class JsonPlayistKey {
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String URI = "uri";
        public static final String ARTWORK_URL = "artwork_url";
    }
}
