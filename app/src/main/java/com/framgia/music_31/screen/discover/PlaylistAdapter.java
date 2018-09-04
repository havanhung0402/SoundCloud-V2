package com.framgia.music_31.screen.discover;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Playlist;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by hungdev on 31/08/2018.
 */

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder> {

    private List<Playlist> mPlaylists;
    private View itemView;

    public PlaylistAdapter(List<Playlist> playlists) {
        mPlaylists = playlists;
    }

    @Override
    public PlaylistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_playlist, parent, false);
        return new PlaylistViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlaylistViewHolder holder, int position) {
        holder.fillData(mPlaylists.get(position));
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

        private void fillData(Playlist playlist) {
            mTextTitle.setText(playlist.getTitle());
            Picasso.with(itemView.getContext()).load(playlist.getUrlImage()).into(mImageItem);
        }
    }
}
