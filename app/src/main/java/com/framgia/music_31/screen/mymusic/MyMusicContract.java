package com.framgia.music_31.screen.mymusic;

import com.framgia.music_31.BasePresenter;
import com.framgia.music_31.BaseView;
import com.framgia.music_31.screen.discover.DiscoverContract;

/**
 * Created by hungdev on 03/09/2018.
 */

public interface MyMusicContract {
    interface View extends BaseView<DiscoverContract.Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}