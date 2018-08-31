package com.framgia.music_31;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyMuiscFragment extends Fragment {

    public static  MyMuiscFragment newInstance() {
        MyMuiscFragment myMuiscFragment = new MyMuiscFragment();
        return myMuiscFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_muisc, container, false);
    }
}
