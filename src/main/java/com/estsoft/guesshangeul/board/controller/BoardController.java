package com.estsoft.guesshangeul.board.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.board.dto.BoardResponse;
import com.estsoft.guesshangeul.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardController {
	private final BoardService boardService;

	@GetMapping
	public ResponseEntity<List<BoardResponse>> readTotalBoard(
		@PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		List<BoardResponse> response = boardService.readTotalBoard(pageable);

		return ResponseEntity.ok(response);
	}
}
