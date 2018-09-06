package com.framgia.music_31;

/**
 * Created by hungdev on 02/09/2018.
 */

public interface BasePresenter<T> {

    void start();

    void setView(T view);
}
