package com.example.android_music_player.library.songs;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_music_player.MainActivity;
import com.example.android_music_player.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Songs_Fr extends Fragment {

    public Songs_Fr() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_songs_, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.song_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getView().getContext());
        recyclerView.setLayoutManager(layoutManager);

        ListenerInterfaceContainer container = new ListenerInterfaceContainer();

        MainActivity.songsRecyclerAdapter = new SongsRecyclerAdapter(MainActivity.mediaData.getSongsList(),this.getActivity(), container.s);

        recyclerView.setAdapter(MainActivity.songsRecyclerAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        //Reinicialize songsRecyclerAdapter after coming from album view
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.song_list);
        ListenerInterfaceContainer container = new ListenerInterfaceContainer();
        MainActivity.songsRecyclerAdapter = new SongsRecyclerAdapter(MainActivity.mediaData.getSongsList(),this.getActivity(), container.s);
        recyclerView.setAdapter(MainActivity.songsRecyclerAdapter);
    }
}
