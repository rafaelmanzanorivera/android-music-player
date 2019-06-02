package com.example.android_music_player.playlists.PlaylistSongs;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_music_player.MainActivity;
import com.example.android_music_player.R;
import com.example.android_music_player.data.AudioModel;
import com.example.android_music_player.playlists.database.PlaylistDataHelper;

import java.util.ArrayList;

public class PlaylistSongs_Fr extends ListFragment implements AdapterView.OnItemClickListener, MediaPlayer.OnCompletionListener {

    ArrayList<String> songs;
    String lastPlayedSong;
    ListView listView;

    public PlaylistSongs_Fr() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlist_songs, container, false);

    }

    public static String[] GetStringArray(ArrayList<Integer> arr)
    {

        String str[] = new String[arr.size()];
        Object[] objArr = arr.toArray();
        int i = 0;
        for (Object obj : objArr) {
            str[i++] = Integer.toString((Integer)obj);
        }
        return str;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        songs = new ArrayList<>();
        SQLiteOpenHelper playlistDHelper = new PlaylistDataHelper(getContext());

        final SQLiteDatabase db = playlistDHelper.getReadableDatabase();

        String playlistId = getActivity().getIntent().getStringExtra("playlist_id");
        Toast.makeText(getActivity(), playlistId, Toast.LENGTH_SHORT).show();

        Cursor cursor = db.query("playlists_songs", new String[]{"_id","song_id"},"_id=?",new String[]{playlistId},null,null,null,null);

        ArrayList<Integer> s = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int name = cursor.getInt(cursor.getColumnIndex("song_id"));

                s.add(name);
                cursor.moveToNext();
            }
        }
        String [] ids = new String[s.size()];
        String query = "SELECT * FROM songs"
                + " WHERE _id IN (" + makePlaceholders(s.size()) + ")";


        Cursor cursor2 = db.rawQuery(query, GetStringArray(s));

        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(),R.layout.song_in_playlist_item,cursor2, new String[]{"name"},new int[]{R.id.item_song_name_in_playlist},0);
        setListAdapter(adapter);

        Cursor cursor3 = db.rawQuery(query, GetStringArray(s));
        if (cursor3.moveToFirst()) {
            while (!cursor3.isAfterLast()) {
                String name = cursor3.getString(cursor3.getColumnIndex("name"));
                songs.add(name);
                cursor3.moveToNext();
            }
        }
        cursor3.close();

        getListView().setOnItemClickListener(this);
        MainActivity.audioPlayer.mediaPlayer.setOnCompletionListener(this);
        reloadPlayPauseLayout();

    }


    String makePlaceholders(int len) {
        if (len < 1) {
            // It will lead to an invalid query anyway ..
            //throw new RuntimeException("No placeholders");
            return null;
        } else {
            StringBuilder sb = new StringBuilder(len * 2 - 1);
            sb.append("?");
            for (int i = 1; i < len; i++) {
                sb.append(",?");
            }
            return sb.toString();
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position,long id)
    {
        ListView listView = getView().findViewById(android.R.id.list);

        TextView songName = view.findViewById(R.id.item_song_name_in_playlist);

        AudioModel song = MainActivity.mediaData.getSongWithName(songName.getText().toString());

        if(MainActivity.audioPlayer.mediaPlayer.isPlaying())
        {
            if(MainActivity.audioPlayer.playingSong.getaName().equals(song.getaName()))
                MainActivity.audioPlayer.pause();
            else
            {
                MainActivity.audioPlayer.play(song);
                lastPlayedSong = song.getaName();
            }

        }
        else
            MainActivity.audioPlayer.play(song);


        Toast.makeText(getActivity(), songName.getText().toString(), Toast.LENGTH_SHORT).show();

        reloadPlayPauseLayout();//TODO mp.setOnCompletionListener(this);


    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    public void reloadPlayPauseLayout()
    {

        if(MainActivity.audioPlayer.mediaPlayer.isPlaying())
        {
            String playing_song = MainActivity.audioPlayer.playingSong.getaName();
            ListView listView = getListView();

            for( int i = 0; i < getListAdapter().getCount(); i++)
            {
                View itemView = getViewByPosition(i,listView);

                if(playing_song.startsWith( ((TextView)itemView.findViewById((R.id.item_song_name_in_playlist))).getText().toString()) && !MainActivity.audioPlayer.isPaused)
                {
                    itemView.findViewById(R.id.play_icon).setVisibility(View.INVISIBLE);
                    itemView.findViewById(R.id.pause_icon).setVisibility(View.VISIBLE);

                }
                else
                {
                    itemView.findViewById(R.id.play_icon).setVisibility(View.VISIBLE);
                    itemView.findViewById(R.id.pause_icon).setVisibility(View.INVISIBLE);
                }

            }
        }
        else
        {
            ListView listView = getListView();

            for( int i = 0; i < getListAdapter().getCount(); i++)
            {
                View itemView = getViewByPosition(i,listView);

                itemView.findViewById(R.id.play_icon).setVisibility(View.VISIBLE);
                itemView.findViewById(R.id.pause_icon).setVisibility(View.INVISIBLE);

            }
        }


    }



    @Override
    public void onCompletion(MediaPlayer mp)
    {
        int i;
        //Get index of song
        for(i = 0; i<songs.size();i++)
        {
            if(lastPlayedSong.equals(songs.get(i)))
                break;
        }

        if(i<songs.size()-1)
        {
            AudioModel nextSong = MainActivity.mediaData.getSongWithName(songs.get(i+1));
            MainActivity.audioPlayer.play(nextSong);
            lastPlayedSong = nextSong.getaName();
            //reloadPlayPauseLayout();//TODO
        }
    }
}
