package com.framgia.music_31.screens.player;

import android.util.Log;

/**
 * Created by hungdev on 09/09/2018.
 */

public class PlayerPresenter implements PlayerContract.Presenter{

    private PlayerContract.View mView;


    @Override
    public void start() {

    }

    @Override
    public void setView(PlayerContract.View view) {
        mView = view;
    }
}
