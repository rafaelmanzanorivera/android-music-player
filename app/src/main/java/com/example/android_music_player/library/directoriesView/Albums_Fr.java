package com.example.android_music_player.library.directoriesView;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_music_player.MainActivity;
import com.example.android_music_player.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Albums_Fr extends Fragment{



    public Albums_Fr() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        RecyclerView recyclerView = getView().findViewById(R.id.directory_grid);

        Context a = this.getActivity();

        GridLayoutManager layoutManager = new GridLayoutManager(getView().getContext(),3);
        recyclerView.setLayoutManager(layoutManager);

        DirectoryRecyclerAdapter adapter = new DirectoryRecyclerAdapter(MainActivity.mediaData.getDirectoriesList(),a);
        recyclerView.setAdapter(adapter);
    }
}
