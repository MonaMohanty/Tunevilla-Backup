package com.kodnest.tunehub.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehub.Entity.Song;
import com.kodnest.tunehub.Entity.User;
import com.kodnest.tunehub.ServiceImpl.SongServiceImpl;
import com.kodnest.tunehub.ServiceImpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserServiceImpl serviceImpl;

	@Autowired
	SongServiceImpl service;

	@PostMapping("/register")
	public String addUser(@ModelAttribute User user) {
		// System.out.println(user.getUsername()+" "+user.getEmail()+"
		// "+user.getPassword()
		// +" "+user.getGender()+" "+user.getRole()+" "+user.getAdd());

		// email taken from registration form
		String email = user.getEmail();

		// checking if email as entered in registration from is present in DB or not.
		boolean status = serviceImpl.emailExists(email);
		if (status == false) {
			serviceImpl.addUser(user);
			System.out.println("User added");
		} else {
			System.out.println("User already exists.");
		}
		return "login";
	}

	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email, String password, HttpSession session, Model model) {
		if (serviceImpl.validateUser(email, password) == true) {
			String role = serviceImpl.getRole(email);
			session.setAttribute("email", email);

			if (role.equals("admin")) {
				return "adminhome";
			}

			else {
				User user = serviceImpl.getUser(email);
				boolean sts = user.isIspremium();
				List<Song> fetch = service.fectchAllSongs();
				model.addAttribute("songs", fetch);

				model.addAttribute("ispremium", sts);
				return "customerhome";
			}
		} else {
			return "login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}