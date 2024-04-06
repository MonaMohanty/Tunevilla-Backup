package com.kodnest.tunehub.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.Entity.Playlist;
import com.kodnest.tunehub.Entity.Song;
import com.kodnest.tunehub.Service.PlaylistService;
import com.kodnest.tunehub.Service.SongService;

@Controller
public class PlayListController {

	@Autowired
	SongService service;

	@Autowired
	PlaylistService playSer;

	@GetMapping("/createplaylist")
	public String createPlaylist(Model model) {
		List<Song> songList = service.fectchAllSongs();
		model.addAttribute("songs", songList);

		return "createplaylist";
	}

	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist play) {
		// updating the playlist table
		playSer.addPlaylist(play);

		// updating the song table
		List<Song> songList = play.getSongs();
		for (Song s : songList) {
			s.getPlaylist().add(play);
			service.updateSong(s);
		}
		return "adminhome";
	}

	@GetMapping("/viewplaylist")
	public String viewPlaylist(Model model) {
		List<Playlist> playlist = playSer.fectchAllPlaylist();
		model.addAttribute("list", playlist);
		return "displayplaylist";
	}

}
