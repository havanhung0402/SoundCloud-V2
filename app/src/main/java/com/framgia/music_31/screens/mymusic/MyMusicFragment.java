package com.framgia.music_31.screens.mymusic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.music_31.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyMusicFragment extends Fragment {

    public static MyMusicFragment newInstance() {
        MyMusicFragment myMusicFragment = new MyMusicFragment();
        return myMusicFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_muisc, container, false);
    }
}
