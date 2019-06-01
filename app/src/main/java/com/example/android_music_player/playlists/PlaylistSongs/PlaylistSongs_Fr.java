package com.example.android_music_player.playlists.PlaylistSongs;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.telephony.mbms.StreamingServiceInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.android_music_player.R;
import com.example.android_music_player.playlists.database.PlaylistDataHelper;

import java.util.ArrayList;

public class PlaylistSongs_Fr extends ListFragment {


    public PlaylistSongs_Fr() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlist_songs, container, false);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        SQLiteOpenHelper playlistDHelper = new PlaylistDataHelper(getContext());

        final SQLiteDatabase db = playlistDHelper.getReadableDatabase();

        String playlistId = getActivity().getIntent().getStringExtra("playlist_id");
        Toast.makeText(getActivity(), playlistId, Toast.LENGTH_SHORT).show();
        //Get songs ids of playlist
        //Cursor cursor = db.query("playlists_songs", new String[]{"_id","name"},"playlist_id =? ",new String[]{playlistId},null,null,null,null);

        //Show songs with selected id

//        String Query = "" +
//                "Select s.name " +
//                "from songs s " +
//                "join playlists_songs ps on s._id=ps.song_id " +
//                "join playlists p on ps.playlist_id=p._id " +
//                "where p._id = " + playlistId;


//        String Query = "" +
//                "Select name from songs where _id in" +
//                "(" +
//                "   select song_id from playlists_songs where playlist_id=" +playlistId +
//                ")";

//        String Query = "select song_id from playlists_songs where playlist_id=" +playlistId;

        String Query = "select * from playlists";//" where song_id=" +playlistId;


//        String TableName = "songs";
//        String dbfield = "name";
//

//        String Query = "Select * from " + TableName + " where " + dbfield + " = " + "'" + fieldValue + "'" ;
//        Cursor cursor = db.rawQuery(Query, null);

//        Log.d("sqlmm",Query);
//
//        Cursor cursor = db.rawQuery(Query,null);
//
//        int cols = cursor.getColumnCount();
//        int pos = cursor.getPosition();
//        cursor.moveToFirst();
//        String is = getString(cursor.getColumnIndex("name"));


        Cursor cursor = db.query("playlists_songs", new String[]{"_id","song_id"},"_id=?",new String[]{playlistId},null,null,null,null);


        ArrayList<String> s = new ArrayList<>();
        // = new ArrayList<>();

        cursor.moveToFirst();
        for(int i = 0; i< cursor.getCount(); i++)
        {
            s.add(cursor.getString(cursor.getColumnIndex("song_id")));
            cursor.moveToNext();
        }
        String [] ids = new String[s.size()];



        String query = "SELECT * FROM songs"
                + " WHERE _id IN (" + makePlaceholders(s.size()) + ")";


        Cursor cursor2 = db.rawQuery(query, GetStringArray(s));


        //rawQuery("SELECT id, name FROM people WHERE name = ? AND id = ?", new String[] {"David", "2"});

        int count = cursor.getCount();

        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),MainActivity.groups, android.R.layout.simple_list_item_1);
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(),R.layout.song_in_playlist_item,cursor2, new String[]{"name"},new int[]{R.id.item_song_name_in_playlist},0);
        setListAdapter(adapter);
        //getListView().setOnItemClickListener(this);




    }

    public static String[] GetStringArray(ArrayList<String> arr)
    {

        // declaration and initialise String Array
        String str[] = new String[arr.size()];

        // Convert ArrayList to object array
        Object[] objArr = arr.toArray();

        // Iterating and converting to String
        int i = 0;
        for (Object obj : objArr) {
            str[i++] = (String)obj;
        }

        return str;
    }

    String makePlaceholders(int len) {
        if (len < 1) {
            // It will lead to an invalid query anyway ..
            throw new RuntimeException("No placeholders");
        } else {
            StringBuilder sb = new StringBuilder(len * 2 - 1);
            sb.append("?");
            for (int i = 1; i < len; i++) {
                sb.append(",?");
            }
            return sb.toString();
        }
    }

}
