package com.framgia.music_31.screens.player;

/**
 * Created by hungdev on 09/09/2018.
 */

public class PlayerMusicPresenter implements PlayerMusicContract.Presenter{

    private PlayerMusicContract.View mView;


    @Override
    public void start() {

    }

    @Override
    public void setView(PlayerMusicContract.View view) {
        mView = view;
    }
}
