package com.kodnest.tunehub.Service;

import java.util.List;

import com.kodnest.tunehub.Entity.Playlist;
import com.kodnest.tunehub.Entity.Song;

public interface SongService {

	public String addSong(Song song);

	public List<Song> fectchAllSongs();

	public boolean songExists(String name);

	public void updateSong(Song s);

}
