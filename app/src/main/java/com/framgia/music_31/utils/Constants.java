package com.framgia.music_31.utils;

/**
 * Created by hungdev on 01/09/2018.
 */

public class Constants {

    //api
    public static final String BASE_URL_V2 = "https://api-v2.soundcloud.com/";
    public static final String CLIENT_ID = "client_id=";
    public static final String TRACKS = "tracks";
    public static final String CHARTS = "charts";
    public static final String SELECTION = "selections";
    public static final String PARAM_KIND = "kind=top";
    public static final String PARAM_GENRE = "genre=soundcloud%3Agenres%3A";
    public static final String BASE_URL = "https://api.soundcloud.com/";
    public static final String STREAM = "stream";
    public static final String QUESTION_MARK = "?";
    public static final String SLASH = "/";
    public static final String AMPERSAND = "&";
    public static final String SLASH_N = "\n";

    public static final String ACTION_NEXT = "com.framgia.music_31.ACTION_NEXT";
    public static final String ACTION_PREVIOUS = "com.framgia.music_31.ACTION_PREVIOUS";
    public static final String ACTION_COMPLETE = "com.framgia.music_31.ACTION_COMPLETE";
    public static final String ACTION_PLAY_CONTROL = "com.framgia.music_31.ACTION_PLAY_CONTROL";
    public static final String ACTION_CLEAR = "com.framgia.music_31.ACTION_CLEAR";
    public static final String ACTION_CURRENT_TIME_CHANGED = "com.framgia.music_31.ACTION_CURRENT_TIME_CHANGED";

    public static long getCurrentTime(int progress, long total){
        double currentTime = ((double)total / 100) * progress;
        return (long) currentTime;
    }

    public static int getProgress(long currentTime, long total){
        double progress = ((double)currentTime / total) * 100;
        return (int) progress;
    }
}
