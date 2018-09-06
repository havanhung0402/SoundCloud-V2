package com.framgia.music_31.screens.song;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Song;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungdev on 07/09/2018.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private List<Song> mSongs;

    public SongAdapter(List<Song> songs) {
        mSongs = new ArrayList<>();
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new SongViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mSongs == null ? 0 : mSongs.size();
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextSongName;
        private TextView mTextSingerName;
        private ImageView mImageItem;
        private ImageView mImageMenu;

        public SongViewHolder(View itemView) {
            super(itemView);
            mTextSongName = itemView.findViewById(R.id.text_song);
            mTextSingerName = itemView.findViewById(R.id.text_artist);
            mImageItem = itemView.findViewById(R.id.image_song);
            mImageMenu = itemView.findViewById(R.id.image_menu);
        }

        private void fillData(Song song) {
            mTextSongName.setText(song.getSongName());
            mTextSingerName.setText(song.getSingerName());
            Picasso.with(itemView.getContext()).load(song.getUrlImage()).into(mImageItem);
            mImageMenu.setImageResource(R.drawable.ic_more_vert_gray_24dp);
        }
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        holder.fillData(mSongs.get(position));
    }

    public void addDataSong(List<Song> songs) {
        mSongs.addAll(songs);
        notifyDataSetChanged();
    }
}
