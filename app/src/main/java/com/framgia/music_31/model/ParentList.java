package com.framgia.music_31.model;

import java.util.List;

/**
 * Created by hungdev on 31/08/2018.
 */

public class ParentList {
    private String mTitle;
    private List<Playlist> mPlaylists;

    public ParentList() {
    }

    public ParentList(String title, List playlists) {
        mTitle = title;
        mPlaylists = playlists;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public List<Playlist> getPlaylists() {
        return mPlaylists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        mPlaylists = playlists;
    }
}
