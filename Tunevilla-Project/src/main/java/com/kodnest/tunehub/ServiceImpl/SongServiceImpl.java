package com.kodnest.tunehub.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.Entity.Playlist;
import com.kodnest.tunehub.Entity.Song;
import com.kodnest.tunehub.Repository.PlaylistRepository;
import com.kodnest.tunehub.Repository.SongRepository;
import com.kodnest.tunehub.Service.SongService;

@Service
public class SongServiceImpl implements SongService {
	@Autowired
	SongRepository songRepo;

	public String addSong(Song song) {
		songRepo.save(song);
		return "song added successfully";

	}

	@Override
	public List<Song> fectchAllSongs() {
		List<Song> songs = songRepo.findAll();
		return songs;
	}

	@Override
	public boolean songExists(String name) {
		if (songRepo.findByName(name) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void updateSong(Song s) {
		songRepo.save(s);
	}
}
