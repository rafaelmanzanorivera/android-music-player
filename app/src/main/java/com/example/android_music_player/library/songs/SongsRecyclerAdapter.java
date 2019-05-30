package com.example.android_music_player.library.songs;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android_music_player.R;
import com.example.android_music_player.data.AudioModel;

import java.util.List;

public class SongsRecyclerAdapter extends RecyclerView.Adapter<SongsRecyclerAdapter.MyViewHolder>
{
    private List<AudioModel> dataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        public RelativeLayout relativeLayout;
        public MyViewHolder(RelativeLayout v)
        {
            super(v);
            relativeLayout = v;
        }
    }

    public SongsRecyclerAdapter(List<AudioModel> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public SongsRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.song_list_item,viewGroup,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i){
        ((TextView) myViewHolder.relativeLayout.findViewById(R.id.item_song_name)).setText(dataset.get(i).getaName());
        ((TextView) myViewHolder.relativeLayout.findViewById(R.id.item_song_duration)).setText(dataset.get(i).getaAlbum());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
