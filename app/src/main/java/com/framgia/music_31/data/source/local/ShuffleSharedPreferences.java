package com.framgia.music_31.data.source.local;

import android.content.Context;
import android.content.SharedPreferences;
import com.framgia.music_31.utils.Constants;

/**
 * Created by hungdev on 15/09/2018.
 */

public class ShuffleSharedPreferences {

    private static  SharedPreferences.Editor sEditor = null;
    private static  SharedPreferences sSharedPreferences = null;

    public static SharedPreferences.Editor getEditor(Context context) {
        if(sEditor == null){
            sSharedPreferences = context.getSharedPreferences(Constants.PREF_SHUFFLE,Context.MODE_PRIVATE);
            sEditor = sSharedPreferences.edit();
        }
        return sEditor;
    }
    public static SharedPreferences getSharedPreferences(Context context){
        if(sSharedPreferences == null){
            sSharedPreferences = context.getSharedPreferences(Constants.PREF_SHUFFLE,Context.MODE_PRIVATE);
        }
        return sSharedPreferences;
    }
}
