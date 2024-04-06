package com.kodnest.tunehub.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.Entity.Song;
import com.kodnest.tunehub.ServiceImpl.SongServiceImpl;

@Controller
public class SongController {
	@Autowired
	SongServiceImpl service;

	@PostMapping("/addsongs")
	public String addSong(@ModelAttribute Song song) {

		boolean status = service.songExists(song.getName());
		if (status == false) {
			service.addSong(song);
			System.out.println("Song added");
		} else {
			System.out.println("Song already exists.");
		}
		return "adminhome";
	}

	@GetMapping("/viewsong")
	public String viewsong(Model model) {
		List<Song> songList = service.fectchAllSongs();
		model.addAttribute("songs", songList);

		return "displaysongs";
	}

	@GetMapping("/playsongs")
	public String playsongs(Model model) {
		boolean premium = true;
		if (premium) {
			List<Song> songList = service.fectchAllSongs();
			model.addAttribute("songs", songList);
			return "displaysongs";
		} else {
			return "subscription";
		}

	}
}
