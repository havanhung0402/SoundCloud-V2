package com.framgia.music_31.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.framgia.music_31.R;
import com.framgia.music_31.model.ParentList;
import java.util.List;

/**
 * Created by hungdev on 31/08/2018.
 */

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.SuperPlaylistViewHolder>{
    private static final int TYPE_PLAYLISTS = 0;
    private static final int TYPE_GENRES = 1;
    private static final int TYPE_SONGS = 2;

    private List<ParentList> mParentLists;
    private Context mContext;

    public ParentAdapter(List<ParentList> playlists) {
        mParentLists = playlists;
    }

    public ParentAdapter(List<ParentList> parentLists, Context context) {
        mParentLists = parentLists;
        mContext = context;
    }

    @Override
    public SuperPlaylistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parent, parent, false);
        return new SuperPlaylistViewHolder(itemView);

    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case TYPE_PLAYLISTS:
                return TYPE_PLAYLISTS;
            case TYPE_GENRES:
                return TYPE_PLAYLISTS;
            case TYPE_SONGS:
                return TYPE_SONGS;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(SuperPlaylistViewHolder holder, int position) {
        ParentList parentList = mParentLists.get(position);
        RecyclerView.LayoutManager mLayoutManager;
       switch (position){
           case TYPE_PLAYLISTS:
               holder.mTextTitle.setText(parentList.getTitle());
               mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true);
               holder.mRecyclerView.setLayoutManager(mLayoutManager);
               holder.mRecyclerView.setAdapter(new PlaylistAdapter(parentList.getPlaylists()));
               break;
           case TYPE_GENRES:
               holder.mTextTitle.setText(parentList.getTitle());
               mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true);
               holder.mRecyclerView.setLayoutManager(mLayoutManager);
               holder.mRecyclerView.setAdapter(new PlaylistAdapter(parentList.getPlaylists()));
               break;
           case TYPE_SONGS:
               holder.mTextTitle.setText(parentList.getTitle());
               mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, true);
               holder.mRecyclerView.setLayoutManager(mLayoutManager);
               holder.mRecyclerView.setAdapter(new SongsAdapter(parentList.getPlaylists()));
               break;
       }

    }

    @Override
    public int getItemCount() {
        Log.d("Size:", mParentLists.size()+"");
        return mParentLists == null ? 0 : mParentLists.size();
    }

    public class SuperPlaylistViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextTitle;
        private RecyclerView mRecyclerView;

        public SuperPlaylistViewHolder(View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.text_super_title);
            mRecyclerView = itemView.findViewById(R.id.recycler_view);

        }
    }
}
