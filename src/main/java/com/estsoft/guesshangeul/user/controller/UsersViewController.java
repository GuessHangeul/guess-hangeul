package com.estsoft.guesshangeul.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.estsoft.guesshangeul.user.dto.ModifyPwdRequest;
import com.estsoft.guesshangeul.user.service.UsersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UsersViewController {
	private final UsersService usersService;

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

	// 토큰 인증 후 페이지 반환
	@GetMapping("/resetPassword/{token}")
	public String validateResetToken(@PathVariable String token, Model model) {
		String result = usersService.validatePasswordResetToken(token);
		if (result.equals("valid")) {
			model.addAttribute("form", new ModifyPwdRequest(token));
			return "passwordChange";
		} else {
			return "failedVerifyToken";
		}
	}

}
