package com.estsoft.guesshangeul.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UsersViewController {

	@GetMapping("/login")
	public String login(@RequestParam(required = false) String message, Model model) {
		model.addAttribute("message", message);
		return "login";
	}

	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

	@GetMapping("/findUser")
	public String findUser() {
		return "findUser";
	}
}
