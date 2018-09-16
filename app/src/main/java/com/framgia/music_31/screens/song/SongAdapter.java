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

    private SongItemClickListener mSongItemListener;
    private List<Song> mSongs;

    public SongAdapter(List<Song> songs, SongItemClickListener songItemListener) {
        mSongs = new ArrayList<>();
        this.mSongItemListener = songItemListener;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new SongViewHolder(itemView, mSongs, mSongItemListener);
    }

    @Override
    public int getItemCount() {
        return mSongs == null ? 0 : mSongs.size();
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTextSongName;
        private TextView mTextSingerName;
        private ImageView mImageItem;
        private ImageView mImageMenu;
        private SongItemClickListener mSongItemClickListener;
        private List<Song> mSongs;

        public SongViewHolder(View itemView, List<Song> songs,
                SongItemClickListener songItemClickListener) {
            super(itemView);
            mTextSongName = itemView.findViewById(R.id.text_song);
            mTextSingerName = itemView.findViewById(R.id.text_artist);
            mImageItem = itemView.findViewById(R.id.image_song);
            mImageMenu = itemView.findViewById(R.id.image_menu);
            mSongItemClickListener = songItemClickListener;
            mSongs = songs;
            itemView.setOnClickListener(this);
        }

        private void fillData(Song song) {
            mTextSongName.setText(song.getSongName());
            mTextSingerName.setText(song.getSingerName());
            if (song.getUrlImage().equals("null")){
                mImageItem.setImageResource(R.drawable.ic_music_note_gray_24dp);
                mImageItem.setBackgroundResource(R.drawable.image_item_music);
            }else {
                Picasso.with(itemView.getContext()).load(song.getUrlImage()).into(mImageItem);
            }
            mImageMenu.setImageResource(R.drawable.ic_more_vert_gray_24dp);
        }

        @Override
        public void onClick(View v) {
            mSongItemClickListener.onSongClick(mSongs.get(getAdapterPosition()),
                    getAdapterPosition(), mSongs);
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

    public interface SongItemClickListener {
        void onSongClick(Song song, int positon, List<Song> songs);
    }
}
