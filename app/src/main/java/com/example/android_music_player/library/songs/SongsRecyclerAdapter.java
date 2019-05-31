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

import com.example.android_music_player.MainActivity;
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
            ImageView pause = (ImageView)v.findViewById(R.id.pause_icon);

            //Set button listeners
            playBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.playPause(v, getAdapterPosition());
                }
            });

            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.addToPlaylist(v, getAdapterPosition());
                }
            });

            pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.playPause(v, getAdapterPosition());
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
    public SongsRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.song_list_item,viewGroup,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i)
    {
        ((TextView) myViewHolder.relativeLayout.findViewById(R.id.item_song_name)).setText(dataset.get(i).getaName());
        ((TextView) myViewHolder.relativeLayout.findViewById(R.id.item_song_duration)).setText(dataset.get(i).getaAlbum());

        ImageView playIcon = myViewHolder.relativeLayout.findViewById(R.id.play_icon);
        ImageView pauseIcon = myViewHolder.relativeLayout.findViewById(R.id.pause_icon);

        String song = dataset.get(i).getaName();

        //Handle play pause layout

        //If there is a playing song
        if (MainActivity.audioPlayer.playingSong != null)
        {
            //Any song paused case -> all to playable
            if(MainActivity.audioPlayer.isPaused)
            {
                pauseIcon.setVisibility(View.INVISIBLE);
                playIcon.setVisibility(View.VISIBLE);
            }
            //This song is playing -> set to pausable
            else if(song.equals(MainActivity.audioPlayer.playingSong.getaName()))
            {
                pauseIcon.setVisibility(View.VISIBLE);
                playIcon.setVisibility(View.INVISIBLE);
            }
            //Other song is playing -> set to playable
            else
            {
                pauseIcon.setVisibility(View.INVISIBLE);
                playIcon.setVisibility(View.VISIBLE);
            }
        }
        //If there is no playing song -> all playable
        else
        {
            pauseIcon.setVisibility(View.INVISIBLE);
            playIcon.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public interface AdapterListenerInterface {

        void playPause(View v, int position);
        void addToPlaylist(View v, int position);
    }
}
