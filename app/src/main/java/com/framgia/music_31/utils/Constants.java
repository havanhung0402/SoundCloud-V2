package com.framgia.music_31.utils;

/**
 * Created by hungdev on 01/09/2018.
 */

public class Constants {

    public class SoundCloud {
        public static final String BASE_URL = "https://api-v2.soundcloud.com/";
        public static final String CLIENT_ID = "client_id=AJ4pxoFBchG36bmDxD5VwWzwlpDDbuYE";
        public static final String CHARTS = "charts";
        public static final String SELECTION = "selections?";
        public static final String PARAM_KIND = "?kind=top&";
        public static final String PARAM_GENRE = "genre=soundcloud%3Agenres%3A&";
    }

    public class Genre {
        public static final String ALL_MUSIC = "all-music";
        public static final String ALL_AUDIO = "all-audio";
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
        public static final String DOWNLOAD_URL = "download_url";
    }

    public class JsonUserKey {
        public static final String USER_ID = "user_id";
        public static final String USER_NAME = "user_name";
    }
}
