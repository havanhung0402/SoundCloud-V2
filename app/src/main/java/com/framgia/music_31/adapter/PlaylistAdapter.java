package com.framgia.music_31.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.framgia.music_31.R;
import com.framgia.music_31.model.Playlist;
import java.util.List;

/**
 * Created by hungdev on 31/08/2018.
 */

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>{

    private List<Playlist> mPlaylists;

    public PlaylistAdapter(List<Playlist> playlists) {
        mPlaylists = playlists;
    }

    @Override
    public PlaylistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist, parent, false);
        return new PlaylistViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlaylistViewHolder holder, int position) {
        Playlist playlist = mPlaylists.get(position);
        holder.mTextTitle.setText(playlist.getTitle());
        holder.mImageItem.setImageResource(playlist.getUrl_image());
    }

    @Override
    public int getItemCount() {
        return mPlaylists == null ? 0 : mPlaylists.size();
    }

    public class PlaylistViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextTitle;
        private ImageView mImageItem;

        public PlaylistViewHolder(View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.text_title);
            mImageItem = itemView.findViewById(R.id.image_playlist);
        }
    }
}
