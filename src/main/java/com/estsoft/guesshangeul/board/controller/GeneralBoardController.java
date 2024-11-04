package com.estsoft.guesshangeul.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.board.dto.GeneralBoardResponse;
import com.estsoft.guesshangeul.board.service.GeneralBoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/generalBoard")
public class GeneralBoardController {
	private final GeneralBoardService generalBoardService;

	@GetMapping
	public ResponseEntity<List<GeneralBoardResponse>> readAllExistingGeneralBoard() {
		// 삭제되지 않은 일반 게시판 리스트를 반환
		List<GeneralBoardResponse> response = generalBoardService.findAllGeneralBoardByIsDeleted(false);
		return ResponseEntity.ok(response);
	}
}
