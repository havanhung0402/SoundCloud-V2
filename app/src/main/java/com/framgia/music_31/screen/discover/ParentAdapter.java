package com.framgia.music_31.screen.discover;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.framgia.music_31.R;
import com.framgia.music_31.data.model.Parent;
import java.util.List;

/**
 * Created by hungdev on 31/08/2018.
 */

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.SuperPlaylistViewHolder> {
    private static final int TYPE_PLAYLISTS = 0;
    private static final int TYPE_GENRES = 1;
    private static final int TYPE_SONGS = 2;
    private static final int SPAN_COUNT = 2;

    private List<Parent> mParents;
    private View itemView;

    public ParentAdapter(List<Parent> parents) {
        mParents = parents;
    }

    @Override
    public SuperPlaylistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_parent, parent, false);
        return new SuperPlaylistViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case TYPE_PLAYLISTS:
                return TYPE_PLAYLISTS;
            case TYPE_GENRES:
                return TYPE_GENRES;
            case TYPE_SONGS:
                return TYPE_SONGS;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(SuperPlaylistViewHolder holder, int position) {
        RecyclerView.LayoutManager mLayoutManager;
        switch (position) {
            case TYPE_PLAYLISTS:
                mLayoutManager =
                        new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, true);
                holder.fillData(mParents.get(position), mLayoutManager,
                        new PlaylistAdapter(mParents.get(position).getPlaylists()), View.VISIBLE);
                break;
            case TYPE_GENRES:
                mLayoutManager = new GridLayoutManager(itemView.getContext(), SPAN_COUNT);
                holder.fillData(mParents.get(position), mLayoutManager,
                        new GenreAdapter(mParents.get(position).getPlaylists()), View.GONE);
                break;
            case TYPE_SONGS:
                mLayoutManager =
                        new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, true);
                holder.fillData(mParents.get(position), mLayoutManager,
                        new SongAdapter(mParents.get(position).getPlaylists()), View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mParents == null ? 0 : mParents.size();
    }

    public class SuperPlaylistViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextTitle;
        private RecyclerView mRecyclerView;
        private TextView mTextViewMore;

        public SuperPlaylistViewHolder(View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.text_super_title);
            mRecyclerView = itemView.findViewById(R.id.recycler_view);
            mTextViewMore = itemView.findViewById(R.id.text_view_more);
        }

        private void fillData(Parent parent, RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter, int visibility){
            mTextTitle.setText(parent.getTitle());
            mRecyclerView.setLayoutManager(layoutManager);
            mTextViewMore.setVisibility(visibility);
            mRecyclerView.setAdapter(adapter);
        }
    }
}
