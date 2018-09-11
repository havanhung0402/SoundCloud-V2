package com.framgia.music_31.utils;

/**
 * Created by hungdev on 13/09/2018.
 */

public class Utils {

    public static int getCurrentTime(int progress, long total){
        double currentTime = ((double)total / Constants.MAX_PROGRESS) * progress;
        return  (int) currentTime;
    }
}
