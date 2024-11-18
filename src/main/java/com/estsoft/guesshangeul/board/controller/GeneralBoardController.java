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

import com.estsoft.guesshangeul.board.dto.GeneralBoardDto;
import com.estsoft.guesshangeul.board.dto.GeneralBoardResponse;
import com.estsoft.guesshangeul.board.service.GeneralBoardService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/generalBoard")
public class GeneralBoardController {
	private final GeneralBoardService generalBoardService;

	@Operation(summary = "일반 게시판 목록 조회", tags = {"게시판 API"})
	@GetMapping
	public ResponseEntity<List<GeneralBoardResponse>> readAllExistingGeneralBoard(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "id") String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

		// 삭제되지 않은 일반 게시판 리스트를 반환
		List<GeneralBoardDto> result = generalBoardService.findAllGeneralBoardByIsDeleted(false, pageable);
		List<GeneralBoardResponse> response = result.stream().map(GeneralBoardResponse::new).toList();
		return ResponseEntity.ok(response);
	}
}
