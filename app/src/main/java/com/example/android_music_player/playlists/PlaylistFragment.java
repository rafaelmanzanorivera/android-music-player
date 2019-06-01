package com.example.android_music_player.playlists;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_music_player.R;
import com.example.android_music_player.playlists.PlaylistSongs.PlaylistSongsss;
import com.example.android_music_player.playlists.database.PlaylistDataHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends ListFragment implements AdapterView.OnItemClickListener {

    String m_Text = "";


    public PlaylistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlists, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton fab = getView().findViewById(R.id.fab);


        SQLiteOpenHelper playlistDHelper = new PlaylistDataHelper(getContext());

        final SQLiteDatabase db = playlistDHelper.getReadableDatabase();
        playlistDHelper.onCreate(db);
        Cursor cursor = db.query("playlists", new String[]{"_id","name"},null,null,null,null,null,null);

        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),MainActivity.groups, android.R.layout.simple_list_item_1);
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(),R.layout.playlist_item,cursor, new String[]{"name"},new int[]{R.id.item_playlist_name},0);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.MyDialogTheme);
                builder.setTitle("Title");

                // Set up the input
                final EditText input = new EditText(getContext());
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        PlaylistDataHelper.addPlaylist(db,m_Text);
                        Cursor cursor = db.query("playlists", new String[]{"_id","name"},null,null,null,null,null,null);
                        adapter.swapCursor(cursor);
                        adapter.notifyDataSetChanged();
                        Snackbar.make(getView(), "Added playlist", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });




    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();

        TextView playlistName = view.findViewById(R.id.item_playlist_name);

        SQLiteOpenHelper playlistDHelper = new PlaylistDataHelper(getContext());

        SQLiteDatabase db = playlistDHelper.getReadableDatabase();

        String idplaylist = PlaylistDataHelper.getItemId(db,"playlists","name", playlistName.getText().toString());

        Intent intent = new Intent(getActivity(), PlaylistSongsss.class);

        intent.putExtra("playlist_id",idplaylist);

        startActivity(intent);



    }



}
