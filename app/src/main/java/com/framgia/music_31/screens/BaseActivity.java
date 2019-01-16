package com.framgia.music_31.screens;

import android.support.v7.app.AppCompatActivity;

public interface BaseActivity extends AppCompatActivity {

    void initComponents();

    void initData();

    void initView(String url, String action);
}
