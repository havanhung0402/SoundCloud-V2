package com.framgia.music_31.utils;

/**
 * Created by hungdev on 01/09/2018.
 */

public class Constants {

    public class SoundCloud {
        public static final String BASE_URL = "https://api.soundcloud.com/";
        public static final String CLIENT_ID = "client_id=AJ4pxoFBchG36bmDxD5VwWzwlpDDbuYE";
        public static final String TRACKS = "tracks";
        public static final String PLAYLIST = "playlists?q=mrsiro";
    }

    public class Genre {
        public static final String ALTERNATIVE_ROCK = "alternative-rock";
        public static final String AMBIENT = "ambient";
        public static final String CLASSICAL = "classical";
        public static final String COUNTRY = "country";
    }

    public class JsonTrackKey {
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String URI = "url";
        public static final String ARTWORK_URL = "artwork_url";
        public static final String DURATION = "duration";
        public static final String GENRE = "genre";
        public static final String DOWNLOADABLE = "downloadable";
        public static final String DOWNLOAD_URL = "download_url";
    }

    public class JsonUserKey {
        public static final String USER_ID = "user_id";
        public static final String USER_NAME = "user_name";
    }
}
