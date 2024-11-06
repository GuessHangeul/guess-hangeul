package com.estsoft.guesshangeul.userrank.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AddUserRankViewController {
	@GetMapping("/api/boardmanagerApply")
	public String boardManagerApply(@RequestParam(required = false) String message, Model model) {
		model.addAttribute("message", message);
		return "BoardManagerApply";
	}
}
