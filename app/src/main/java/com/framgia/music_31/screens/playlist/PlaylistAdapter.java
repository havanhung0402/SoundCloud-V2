package com.framgia.music_31.screens.playlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Playlist;
import com.framgia.music_31.utils.Constants;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by hungdev on 17/09/2018.
 */

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder> {

    private List<Playlist> mPlaylists;
    private OnItemClick mItemClick;

    public PlaylistAdapter(List<Playlist> playlists, OnItemClick onItemClick) {
        mPlaylists = playlists;
        mItemClick = onItemClick;
    }

    @Override
    public PlaylistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_playlist_gridview, parent, false);
        return new PlaylistViewHolder(itemView, mPlaylists, mItemClick);
    }

    @Override
    public void onBindViewHolder(PlaylistAdapter.PlaylistViewHolder holder, int position) {
        holder.fillData(mPlaylists.get(position));
    }

    @Override
    public int getItemCount() {
        return mPlaylists == null ? 0 : mPlaylists.size();
    }

    public static class PlaylistViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener{

        private TextView mTextTitle;
        private ImageView mImageItem;
        private List<Playlist> mPlaylists;
        private OnItemClick mOnItemClick;

        public PlaylistViewHolder(View itemView, List<Playlist> playlists, OnItemClick onItemClick) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.text_title);
            mImageItem = itemView.findViewById(R.id.image_playlist);
            mPlaylists = playlists;
            mOnItemClick = onItemClick;
            itemView.setOnClickListener(this);
        }

        private void fillData(Playlist playlist) {
            mTextTitle.setText(playlist.getTitle());
            if(!playlist.getUrlImage().equals(Constants.NULL_OBJECT)){
                Picasso.with(itemView.getContext()).load(playlist.getUrlImage()).into(mImageItem);
            }else {
                mImageItem.setImageResource(R.drawable.ic_music_note_gray_24dp);
                mImageItem.setBackgroundResource(R.drawable.image_item_music);
            }
        }

        @Override
        public void onClick(View v) {
            mOnItemClick.onClick(mPlaylists.get(getAdapterPosition()));
        }
    }

    interface OnItemClick {
        void onClick(Playlist playlists);
    }

}