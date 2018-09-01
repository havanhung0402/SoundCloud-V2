package com.framgia.music_31.adapter;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.framgia.music_31.R;
import com.framgia.music_31.Song;
import java.util.List;

/**
 * Created by hungdev on 24/07/2018.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongViewHolder>{

    //private SongItemListener mSongItemListener;
    private List<Song> mSongs;

    public SongsAdapter(List songs) {
        this.mSongs = songs;
        //this.mSongItemListener = songItemListener;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song,
                parent,false);
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
            //itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//            mSongItemListener.onSongClick(mSongs.get(getAdapterPosition()), getAdapterPosition());
//        }
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        Song song = mSongs.get(position);
        holder.mTextSongName.setText(song.getSongName());
        holder.mTextSingerName.setText(song.getSingerName());
        holder.mImageItem.setImageResource(R.drawable.siro);
    }

//    public interface SongItemListener {
//        void onSongClick(Song clickedSong, int positon);
//    }
}
