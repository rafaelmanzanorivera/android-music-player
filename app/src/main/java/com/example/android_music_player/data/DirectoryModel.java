package com.example.android_music_player.data;

import java.util.ArrayList;
import java.util.List;

public class DirectoryModel
{
    private List<AudioModel> songs;
    private String directoryName;

    public DirectoryModel(String directoryName) {
        this.directoryName = directoryName;
        this.songs = new ArrayList<>();
    }

    public List<AudioModel> getSongs() {
        return songs;
    }

    public void addSong(AudioModel song) {
        this.songs.add(song);
    }

    public String getDirectoryName() {
        return directoryName;
    }

}
