package com.framgia.music_31.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hungdev on 24/07/2018.
 */

public class Song implements Parcelable {
    private long mId;
    private String mSongName;
    private String mSingerName;
    private String mUrlImage;
    private byte[] mByteImage;
    private int mDuration;
    private String mUrl;

    public Song() {
    }

    public Song(long id, String songName, String singerName, String urlImage,
            int duration, String url) {
        mId = id;
        mSongName = songName;
        mSingerName = singerName;
        mUrlImage = urlImage;
        mDuration = duration;
        mUrl = url;
    }

    public Song(String songName, String singerName, int duration, String url) {
        mSongName = songName;
        mSingerName = singerName;
        mDuration = duration;
        mUrl = url;
    }

    protected Song(Parcel in) {
        mId = in.readLong();
        mSongName = in.readString();
        mSingerName = in.readString();
        mUrlImage = in.readString();
        mByteImage = in.createByteArray();
        mDuration = in.readInt();
        mUrl = in.readString();
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getSongName() {
        return mSongName;
    }

    public void setSongName(String songName) {
        mSongName = songName;
    }

    public String getSingerName() {
        return mSingerName;
    }

    public void setSingerName(String singerName) {
        mSingerName = singerName;
    }

    public String getUrlImage() {
        return mUrlImage;
    }

    public void setUrlImage(String urlImage) {
        mUrlImage = urlImage;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
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
        dest.writeString(mSongName);
        dest.writeString(mSingerName);
        dest.writeString(mUrlImage);
        dest.writeByteArray(mByteImage);
        dest.writeInt(mDuration);
        dest.writeString(mUrl);
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel source) {
            return new Song(source);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public static class JsonTrackKey {
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String URI = "url";
        public static final String ARTWORK_URL = "artwork_url";
        public static final String DURATION = "duration";
        public static final String GENRE = "genre";
        public static final String DOWNLOADABLE = "downloadable";
        public static final String DOWNLOAD_URL = "download_url";
    }
}
