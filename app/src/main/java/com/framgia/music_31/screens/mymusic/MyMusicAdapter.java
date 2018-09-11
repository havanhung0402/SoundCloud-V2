package com.framgia.music_31.screens.mymusic;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Song;
import com.framgia.music_31.screens.song.SongAdapter;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungdev on 14/09/2018.
 */

public class MyMusicAdapter extends RecyclerView.Adapter<MyMusicAdapter.SongViewHolder> {

    private MyMusicAdapter.SongItemClickListener mSongItemListener;
    private List<Song> mSongs;

    public MyMusicAdapter(MyMusicAdapter.SongItemClickListener songItemListener) {
        mSongs = new ArrayList<>();
        mSongItemListener = songItemListener;
    }

    @Override
    public MyMusicAdapter.SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        private MyMusicAdapter.SongItemClickListener mSongItemClickListener;
        private List<Song> mSongs;

        public SongViewHolder(View itemView, List<Song> songs,
                MyMusicAdapter.SongItemClickListener songItemClickListener) {
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
            mImageItem.setImageResource(R.drawable.ic_music_note_gray_24dp);
            mImageItem.setBackgroundResource(R.drawable.image_item_music);
            mImageMenu.setImageResource(R.drawable.ic_more_vert_gray_24dp);
        }

        @Override
        public void onClick(View v) {
            mSongItemClickListener.onSongClick(mSongs.get(getAdapterPosition()),
                    getAdapterPosition(), mSongs);
        }
    }

    @Override
    public void onBindViewHolder(MyMusicAdapter.SongViewHolder holder, int position) {
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
