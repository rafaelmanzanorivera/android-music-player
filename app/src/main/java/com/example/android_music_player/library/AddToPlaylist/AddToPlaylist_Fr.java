package com.example.android_music_player.library.AddToPlaylist;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.android_music_player.R;
import com.example.android_music_player.data.AudioModel;
import com.example.android_music_player.playlists.database.PlaylistDataHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddToPlaylist_Fr extends ListFragment implements AdapterView.OnItemClickListener {


    public AddToPlaylist_Fr() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_to_playlist_, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton fab = getView().findViewById(R.id.fab);


        SQLiteOpenHelper playlistDHelper = new PlaylistDataHelper(getContext());

        final SQLiteDatabase db = playlistDHelper.getReadableDatabase();
        playlistDHelper.onCreate(db);
        Cursor cursor = db.query("playlists", new String[]{"_id", "name"}, null, null, null, null, null, null);

        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),MainActivity.groups, android.R.layout.simple_list_item_1);
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(), R.layout.playlist_item, cursor, new String[]{"name"}, new int[]{R.id.item_playlist_name}, 0);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {


        TextView playlistName = view.findViewById(R.id.item_playlist_name);

        SQLiteOpenHelper playlistDHelper = new PlaylistDataHelper(getContext());
        final SQLiteDatabase db = playlistDHelper.getReadableDatabase();

        AudioModel song = (AudioModel)getActivity().getIntent().getSerializableExtra("song");


        PlaylistDataHelper.addSongToPlaylist(db,playlistName.getText().toString(),song);

        getActivity().finish();


    }
}
