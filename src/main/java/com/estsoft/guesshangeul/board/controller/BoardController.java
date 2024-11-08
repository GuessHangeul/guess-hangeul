package com.estsoft.guesshangeul.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.board.dto.BoardResponse;
import com.estsoft.guesshangeul.board.dto.GeneralBoardDto;
import com.estsoft.guesshangeul.board.dto.QuizBoardDto;
import com.estsoft.guesshangeul.board.service.GeneralBoardService;
import com.estsoft.guesshangeul.board.service.QuizBoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardController {
	private final GeneralBoardService generalBoardService;
	private final QuizBoardService quizBoardService;

	@GetMapping
	public ResponseEntity<List<BoardResponse>> readAllExistingGeneralBoard(
		@PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		// 삭제되지 않은 게시판 리스트를 반환

		// 일반 게시판 요청
		List<GeneralBoardDto> generalBoardList = generalBoardService.findAllGeneralBoardByIsDeleted(false, pageable);
		List<BoardResponse> generalBoardResponse = generalBoardList.stream().map(BoardResponse::new).toList();
		List<BoardResponse> response = new ArrayList<>(generalBoardResponse);

		// 나온 게시판 목록 개수 제외하여 나머지 개수만큼 요청
		int offset = generalBoardList.size();
		Pageable nextPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort())
			.withPage(pageable.getPageNumber() + (offset / pageable.getPageSize()));

		// 퀴즈 게시판 요청
		List<QuizBoardDto> quizBoardList = quizBoardService.findAllQuizBoardByIsDeleted(false, nextPageable);
		List<BoardResponse> quizBoardResponse = quizBoardList.stream().map(BoardResponse::new).toList();
		response.addAll(quizBoardResponse);

		return ResponseEntity.ok(response);
	}
}
