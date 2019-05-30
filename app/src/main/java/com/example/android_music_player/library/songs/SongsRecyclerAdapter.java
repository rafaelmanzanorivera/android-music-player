package com.example.android_music_player.library.songs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android_music_player.R;
import com.example.android_music_player.data.AudioModel;

import java.util.List;

public class SongsRecyclerAdapter extends RecyclerView.Adapter<SongsRecyclerAdapter.MyViewHolder>
{   private Context mContext;
    private List<AudioModel> dataset;
    public AdapterListenerInterface onClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public RelativeLayout relativeLayout;

        public MyViewHolder(RelativeLayout v)
        {
            super(v);
            ImageView playBtn = (ImageView)v.findViewById(R.id.play_icon);
            ImageView addBtn = (ImageView)v.findViewById(R.id.add_icon);

            playBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.classOnClick(v, getAdapterPosition());
                }
            });

            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.daysOnClick(v, getAdapterPosition());
                }
            });

            relativeLayout = v;
        }
    }

    public SongsRecyclerAdapter(List<AudioModel> dataset, Context c, AdapterListenerInterface listener) {
        this.dataset = dataset;
        this.mContext = c;
        this.onClickListener = listener;
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


    public interface AdapterListenerInterface {

        void classOnClick(View v, int position);
        void daysOnClick(View v, int position);
    }
}
