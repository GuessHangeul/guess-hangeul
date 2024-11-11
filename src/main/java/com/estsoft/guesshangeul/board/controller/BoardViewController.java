package com.estsoft.guesshangeul.board.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.estsoft.guesshangeul.board.dto.BoardResponse;
import com.estsoft.guesshangeul.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardViewController {
	private final BoardService boardService;

	@GetMapping("/createBoard")
	public String createBoard() {
		return "createBoard";
	}

	@GetMapping("/")
	public String home(Model model) {
		List<BoardResponse> boardResponses = boardService.readTotalBoard(Pageable.ofSize(6));
		model.addAttribute("boards", boardResponses);
		return "index";
	}
}