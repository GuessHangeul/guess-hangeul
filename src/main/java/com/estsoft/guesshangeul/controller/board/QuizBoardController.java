package com.estsoft.guesshangeul.controller.board;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.dto.board.QuizBoardResponse;
import com.estsoft.guesshangeul.service.board.QuizBoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/quizBoard")
public class QuizBoardController {
	private final QuizBoardService quizBoardService;

	@GetMapping
	public ResponseEntity<List<QuizBoardResponse>> readAllExistingQuizBoard() {
		// 삭제되지 않은 퀴즈 게시판 리스트를 반환
		List<QuizBoardResponse> response = quizBoardService.findAllQuizBoardByIsDeleted(false);
		return ResponseEntity.ok(response);
	}
}
