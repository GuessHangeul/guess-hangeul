package com.estsoft.guesshangeul.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardViewController {
	@GetMapping("/createBoard")
	public String createBoard() {
		return "createBoard";
	}
}