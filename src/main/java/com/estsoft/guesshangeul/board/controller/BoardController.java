package com.estsoft.guesshangeul.board.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.board.dto.BoardResponse;
import com.estsoft.guesshangeul.board.service.BoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "게시판 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardController {
	private final BoardService boardService;

	@Operation(summary = "전체 게시판 목록 조회", tags = {"게시판 API"})
	@Parameter(name = "page", description = "페이지 번호", example = "0")
	@Parameter(name = "size", description = "페이지 크기", example = "6")
	@Parameter(name = "sort", description = "정렬 기준", example = "id")
	@GetMapping
	public ResponseEntity<List<BoardResponse>> readTotalBoard(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "6") int size,
		@RequestParam(defaultValue = "id") String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

		List<BoardResponse> response = boardService.readTotalBoard(pageable);

		return ResponseEntity.ok(response);
	}
}
