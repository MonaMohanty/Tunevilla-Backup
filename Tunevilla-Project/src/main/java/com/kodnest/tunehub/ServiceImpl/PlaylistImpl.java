package com.kodnest.tunehub.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.Entity.Playlist;

import com.kodnest.tunehub.Repository.PlaylistRepository;
import com.kodnest.tunehub.Service.PlaylistService;

@Service
public class PlaylistImpl implements PlaylistService {
	@Autowired
	PlaylistRepository playReop;

	@Override
	public void addPlaylist(Playlist play) {
		playReop.save(play);

	}

	@Override
	public List<Playlist> fectchAllPlaylist() {
		List<Playlist> list = playReop.findAll();
		return list;
	}
}
