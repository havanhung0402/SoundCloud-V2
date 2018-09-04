package com.framgia.music_31.screens.discover;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Genre;
import java.util.List;

/**
 * Created by hungdev on 01/09/2018.
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {
    private List<Genre> mGenres;

    public GenreAdapter(List genres) {
        mGenres = genres;
    }

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_genre, parent, false);
        return new GenreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GenreViewHolder holder, int position) {
        holder.fillData(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    public static class GenreViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextGenreName;
        private ImageView mImageGenre;

        public GenreViewHolder(View itemView) {
            super(itemView);
            mTextGenreName = itemView.findViewById(R.id.text_genre_name);
            mImageGenre = itemView.findViewById(R.id.image_genre);
        }

        private void fillData(Genre genre) {
            if(genre != null){
                mTextGenreName.setText(genre.getTitle());
                mImageGenre.setImageResource(genre.getImage());
            }
        }
    }
}
