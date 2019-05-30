package com.example.android_music_player.library;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android_music_player.R;
import com.example.android_music_player.data.DirectoryModel;

import java.util.List;

public class DirectoryRecyclerAdapter extends RecyclerView.Adapter<DirectoryRecyclerAdapter.MyViewHolder>
{

    private List<DirectoryModel> dataset;
    private ItemClickListener mClickListener;

    public DirectoryRecyclerAdapter(List<DirectoryModel> dataset){ this.dataset = dataset; }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        public RelativeLayout relativeLayout;
        public MyViewHolder(RelativeLayout v)
        {
            super(v);
            relativeLayout = v;
        }
    }

    @NonNull
    @Override
    public DirectoryRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.directory_grid_item,viewGroup,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @NonNull
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i)
    {
        //TODO show how many items in folder
        ((TextView) myViewHolder.relativeLayout.findViewById(R.id.directory_name)).setText(dataset.get(i).getDirectoryName());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
