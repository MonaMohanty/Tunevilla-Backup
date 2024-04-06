package com.kodnest.tunehub.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/registration")
	public String register() {
		return "registration";
	}

	@GetMapping("/newsongs")
	public String newsongs() {
		return "newsongs";
	}

	@GetMapping("/forgotPassword")
	public String fPassword() {
		return "forgotPassword";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}

}
