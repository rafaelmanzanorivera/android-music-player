package com.example.android_music_player.playlists.database;

import android.app.slice.Slice;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android_music_player.data.AudioModel;
import com.example.android_music_player.data.MediaData;
import com.example.android_music_player.playlists.Playlists;

public class PlaylistDataHelper extends SQLiteOpenHelper
{
    private static final String DBNAME = "playlistdatabase";
    private static final int DBVERSION = 1;


    public PlaylistDataHelper(Context context) {
        super(context, DBNAME,null, DBVERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

//        db.execSQL("DROP TABLE playlists");
//        db.execSQL("DROP TABLE songs");
//        db.execSQL("DROP TABLE playlists_songs");



        db.execSQL("CREATE TABLE IF NOT EXISTS playlists " +
                "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR(100)" +
                ")");

        db.execSQL("CREATE TABLE IF NOT EXISTS songs " +
                "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR(100), " +
                    "artist VARCHAR(100), " +
                    "path VARCHAR(100)" +
                ")");

        db.execSQL("CREATE TABLE IF NOT EXISTS playlists_songs " +
                "(" +
                    "_id INTEGER, " +
                    "song_id INTEGER, " +
                    "FOREIGN KEY(_id) REFERENCES playlists(_id), " +
                    "FOREIGN KEY(song_id) REFERENCES songs(_id)" +
                ")");

//        addPlaylist(db,"Techno");
//        addPlaylist(db,"Smooth");
//
//
//        addSongToPlaylist(db,"Techno",Playlists.mediaData.getSongWithName("Coca"));
//        addSongToPlaylist(db,"Smooth",Playlists.mediaData.getSongWithName("Encount"));
//        addSongToPlaylist(db,"Smooth",Playlists.mediaData.getSongWithName("Dream"));
//        addSongToPlaylist(db,"Smooth",Playlists.mediaData.getSongWithName("Polona"));

    }

    public static void addPlaylist(SQLiteDatabase db,String name)
    {
        ContentValues playlistData = new ContentValues();
        playlistData.put("name",name);
        db.insert("playlists",null,playlistData);
    }

    public static void addSongToPlaylist(SQLiteDatabase db, String playlist, AudioModel song)
    {
        //Check if p exists
       //if(!checkIfRowExists(db,"playlists","name",playlist)

        if(!checkIfRowExists(db,"songs","name",song.getaName()))
        {
            addSong(db,song);
        }

        String songId = getItemId(db,"songs","name",song.getaName());
        String playlistId = getItemId(db,"playlists","name",playlist);

        ContentValues playlistData = new ContentValues();
        playlistData.put("_id",playlistId);
        playlistData.put("song_id", songId);
        db.insert("playlists_songs",null,playlistData);

    }

    public static void addSong(SQLiteDatabase db, AudioModel song)
    {
        String aPath = song.getaPath();
        String aName = song.getaName();
        String aAlbum = song.getaAlbum();
        String aArtist = song.getaArtist();
        ContentValues playlistData = new ContentValues();
        playlistData.put("name",aName);
        playlistData.put("artist",aArtist);
        playlistData.put("path",aPath);
        db.insert("songs",null,playlistData);
    }

    public static boolean checkIfRowExists(SQLiteDatabase db, String TableName, String dbfield, String fieldValue)
    {
        String Query = "Select * from " + TableName + " where " + dbfield + " = " + "'" + fieldValue + "'" ;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public static String getItemId(SQLiteDatabase db, String TableName, String dbfield, String fieldValue)
    {
        String Query = "Select _id from " + TableName + " where " + dbfield + " = " +  "'" + fieldValue +  "'";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        String id = cursor.getString(cursor.getColumnIndex("_id"));
        cursor.close();
        return id;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
