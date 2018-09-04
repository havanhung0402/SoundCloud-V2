package com.framgia.music_31.screens.discover;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Song;
import java.util.List;

/**
 * Created by hungdev on 24/07/2018.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    //private SongItemListener mSongItemListener;
    private List<Song> mSongs;

    public SongAdapter(List songs) {
        mSongs = songs;
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

    public class SongViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextSongName, mTextSingerName;
        private ImageView mImageItem;
        private ImageView mImageMenu;

        public SongViewHolder(View itemView) {
            super(itemView);
            mTextSongName = itemView.findViewById(R.id.text_song);
            mTextSingerName = itemView.findViewById(R.id.text_artist);
            mImageItem = itemView.findViewById(R.id.image_song);
            mImageMenu = itemView.findViewById(R.id.dimensions);
        }

        private void fillData(Song song) {
            mTextSongName.setText(song.getSongName());
            mTextSingerName.setText(song.getSingerName());
            mImageItem.setImageResource(Integer.parseInt(song.getUrlImage()));
            mImageMenu.setImageResource(R.drawable.ic_more_vert_gray_24dp);
        }

    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        holder.fillData(mSongs.get(position));
    }
}
