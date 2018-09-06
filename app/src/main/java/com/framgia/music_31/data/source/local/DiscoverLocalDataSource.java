package com.framgia.music_31.data.source.local;

import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Genre;
import com.framgia.music_31.data.source.CallBack;
import com.framgia.music_31.data.source.DiscoverDataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungdev on 02/09/2018.
 */

public class DiscoverLocalDataSource implements DiscoverDataSource.LocalDataSource {
    private static DiscoverLocalDataSource sInstance;

    private DiscoverLocalDataSource() {

    }

    public static synchronized DiscoverLocalDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new DiscoverLocalDataSource();
        }
        return sInstance;
    }

    @Override
    public void getGenre(CallBack callback) {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(Genre.ALL_MUSIC, R.drawable.ic_country, Genre.ALL_MUSIC_PARAM));
        genres.add(new Genre(Genre.ALL_AUDIO, R.drawable.ic_classical, Genre.ALL_AUDIO_PARAM));
        genres.add(new Genre(Genre.ALTERNATIVE_ROCK, R.drawable.ic_rock,
                Genre.ALTERNATIVE_ROCK_PARAM));
        genres.add(new Genre(Genre.AMBIENT, R.drawable.ic_ambient, Genre.AMBIENT_PARAM));
        genres.add(new Genre(Genre.CLASSICAL, R.drawable.ic_classical, Genre.CLASSICAL_PARAM));
        genres.add(new Genre(Genre.COUNTRY, R.drawable.ic_country, Genre.COUNTRY_PARAM));
        callback.onSusscess(genres);
    }
}
