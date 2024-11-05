package com.estsoft.guesshangeul.board.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.board.dto.QuizBoardCreateRequest;
import com.estsoft.guesshangeul.board.dto.QuizBoardDto;
import com.estsoft.guesshangeul.board.dto.QuizBoardResponse;
import com.estsoft.guesshangeul.board.service.QuizBoardService;
import com.estsoft.guesshangeul.exception.QuizBoardTitleDuplicateException;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.service.UsersService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/quizBoard")
public class QuizBoardController {
	private final UsersService usersService;
	private final QuizBoardService quizBoardService;

	public void verifyTitleCreateRequest(QuizBoardCreateRequest request) {
		// 제목이 기존 문제 게시판에 존재하는 제목인지 확인
		String title = request.getTitle();
		if (quizBoardService.quizBoardTitleExists(title)) {
			throw new QuizBoardTitleDuplicateException(title);
		}
	}

	@GetMapping
	public ResponseEntity<List<QuizBoardResponse>> readAllExistingQuizBoard(
		@PageableDefault(size = 6, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		// 삭제되지 않은 퀴즈 게시판 리스트를 반환
		List<QuizBoardDto> result = quizBoardService.findAllQuizBoardByIsDeleted(false, pageable);
		List<QuizBoardResponse> response = result.stream().map(QuizBoardResponse::new).toList();
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<QuizBoardResponse> createQuizBoard(@RequestBody QuizBoardCreateRequest request,
		@AuthenticationPrincipal Users user) {
		// 새 문제 게시판을 생성
		// 입력 유효성 검증
		verifyTitleCreateRequest(request);

		QuizBoardDto result = quizBoardService.addNewQuizBoard(request);
		QuizBoardResponse response = new QuizBoardResponse(result);

		return ResponseEntity.ok(response);
	}
}
