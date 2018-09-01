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
    private String mUrl;

    public Song() {
    }

    public Song(long id, String songName, String singerName, String urlImage, String uri) {
        mId = id;
        mSongName = songName;
        mSingerName = singerName;
        mUrlImage = urlImage;
        mUrl = uri;
    }

    protected Song(Parcel in) {
        mId = in.readLong();
        mSongName = in.readString();
        mSingerName = in.readString();
        mUrlImage = in.readString();
        mUrl = in.readString();
    }

    public static final Creator<Song> SONG_CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

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
}
