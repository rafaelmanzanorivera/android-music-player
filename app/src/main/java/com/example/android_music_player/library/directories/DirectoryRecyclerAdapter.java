package com.example.android_music_player.library.directories;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_music_player.MainActivity;
import com.example.android_music_player.R;
import com.example.android_music_player.data.DirectoryModel;

import java.util.List;

public class DirectoryRecyclerAdapter extends RecyclerView.Adapter<DirectoryRecyclerAdapter.MyViewHolder>
{

    private List<DirectoryModel> dataset;
    private ItemClickListener mClickListener;

    private Context context;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };


    public DirectoryRecyclerAdapter(List<DirectoryModel> dataset, Context context){ this.dataset = dataset; this.context = context; }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public RelativeLayout relativeLayout;
        public ImageView dirImage;
        private Context context;

        public MyViewHolder(RelativeLayout v, Context c)
        {
            super(v);
            context = c;
            relativeLayout = v;

            dirImage = (ImageView) v.findViewById(R.id.dir_image);

            dirImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, v+"js" , Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public DirectoryRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.directory_grid_item,viewGroup,false);
        MyViewHolder vh = new MyViewHolder(v,context);
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
