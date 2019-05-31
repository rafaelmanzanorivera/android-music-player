package com.example.android_music_player.playlists.database;

import android.app.slice.Slice;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlaylistDataHelper extends SQLiteOpenHelper
{
    private static final String DBNAME = "playlistdatabase";
    private static final int DBVERSION = 1;


    public PlaylistDataHelper(Context context) {
        super(context, DBNAME,null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE playlists");
        db.execSQL("DROP TABLE songs");
        db.execSQL("DROP TABLE playlists_songs");



        db.execSQL("CREATE TABLE playlists " +
                "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR(100)" +
                ")");

        db.execSQL("CREATE TABLE SONGS " +
                "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR(100), " +
                    "artist VARCHAR(100), " +
                    "path VARCHAR(100)" +
                ")");

        db.execSQL("CREATE TABLE playlists_songs " +
                "(" +
                    "PLAYLIST_ID INTEGER, " +
                    "SONG_ID INTEGER, " +
                    "FOREIGN KEY(PLAYLIST_ID) REFERENCES PLAYLISTS(_id), " +
                    "FOREIGN KEY(SONG_ID) REFERENCES SONGS(_id)" +
                ")");

        addPlaylist(db,"Techno");
        addPlaylist(db,"Smooth");
    }

    public static void addPlaylist(SQLiteDatabase db,String name)
    {
        ContentValues playlistData = new ContentValues();
        playlistData.put("name",name);
        db.insert("playlists",null,playlistData);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
