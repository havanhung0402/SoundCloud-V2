package com.framgia.music_31.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hungdev on 31/08/2018.
 */

public class Playlist implements Parcelable {

    private long mId;
    private String mTitle;
    private String mUrlImage;
    private String mUrl;

    public Playlist() {
    }

    public Playlist(long id, String title, String urlImage, String url) {
        mId = id;
        mTitle = title;
        mUrlImage = urlImage;
        mUrl = url;
    }

    protected Playlist(Parcel in) {
        mId = in.readLong();
        mTitle = in.readString();
        mUrlImage = in.readString();
        mUrl = in.readString();
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
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

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mTitle);
        dest.writeString(mUrlImage);
        dest.writeString(mUrl);
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
