package com.framgia.music_31.data.source;

/**
 * Created by hungdev on 07/09/2018.
 */

public interface CallBack<T> {

    void onSusscess(T datas);

    void onError(Exception e);
}
