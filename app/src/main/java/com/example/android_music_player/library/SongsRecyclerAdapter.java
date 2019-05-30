package com.example.android_music_player.library;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android_music_player.R;
import com.example.android_music_player.data.AudioModel;

import java.util.List;

public class SongsRecyclerAdapter extends RecyclerView.Adapter<SongsRecyclerAdapter.MyViewHolder>
{
    private List<AudioModel> dataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        public LinearLayout linearLayout;
        public MyViewHolder(LinearLayout v)
        {
            super(v);
            linearLayout = v;
        }
    }

    public SongsRecyclerAdapter(List<AudioModel> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public SongsRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.song_list_item,viewGroup,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i){
        ((TextView) myViewHolder.linearLayout.findViewById(R.id.item_song_name)).setText(dataset.get(i).getaName());
        ((TextView) myViewHolder.linearLayout.findViewById(R.id.item_song_duration)).setText(dataset.get(i).getaAlbum());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
