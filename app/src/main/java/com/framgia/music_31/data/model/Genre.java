package com.framgia.music_31.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by hungdev on 01/09/2018.
 */

public class Genre implements Parcelable{
    public static final String ALL_MUSIC= "ALL MUSIC";
    public static final String ALL_AUDIO= "ALL AUDIO";
    public static final String ALTERNATIVE_ROCK = "ALTERNATIVE ROCK";
    public static final String AMBIENT = "AMBIENT";
    public static final String CLASSICAL = "CLASSICAL";
    public static final String COUNTRY= "COUNTRY";

    public static final String ALL_MUSIC_PARAM = "all-music";
    public static final String ALL_AUDIO_PARAM = "all-audio";
    public static final String ALTERNATIVE_ROCK_PARAM = "alternative-rock";
    public static final String AMBIENT_PARAM = "ambient";
    public static final String CLASSICAL_PARAM = "classical";
    public static final String COUNTRY_PARAM = "country";

    @StringDef({ALL_MUSIC_PARAM, ALL_AUDIO_PARAM, ALTERNATIVE_ROCK_PARAM, AMBIENT_PARAM, CLASSICAL_PARAM, COUNTRY_PARAM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ParamGenre {}

    @StringDef({ALL_MUSIC, ALL_AUDIO, ALTERNATIVE_ROCK, AMBIENT, CLASSICAL, COUNTRY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Title {}

    private String mTitle;
    private int mImage;
    private String mParamGenre;

    public Genre() {
    }

    public Genre(@Title String title, int image, @ParamGenre String paramGenre) {
        mTitle = title;
        mImage = image;
        mParamGenre = paramGenre;
    }

    protected Genre(Parcel in) {
        mTitle = in.readString();
        mImage = in.readInt();
        mParamGenre = in.readString();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(@Title String title) {
        mTitle = title;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    public String getParamGenre() {
        return mParamGenre;
    }

    public void setParamGenre(@ParamGenre String paramGenre) {
        mParamGenre = paramGenre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeInt(mImage);
        dest.writeString(mParamGenre);
    }

    public static final Creator<Genre> CREATOR = new Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel source) {
            return new Genre(source);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };
}
