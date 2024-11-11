package com.estsoft.guesshangeul.post.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.post.dto.QuizPostWithCommentCountResponse;
import com.estsoft.guesshangeul.post.service.QuizPostService;

@RestController
@RequestMapping("/api/quizBoard/{quizBoardId}/quizPost")
public class QuizPostController {
	private final QuizPostService quizPostService;

	public QuizPostController(QuizPostService quizPostService) {
		this.quizPostService = quizPostService;
	}

	// 전체 게시글 조회
	@GetMapping
	public ResponseEntity<List<QuizPostWithCommentCountResponse>> getAllGeneralPostsWithCommentCount(
		@PathVariable Long quizBoardId,
		@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
		List<QuizPostWithCommentCountResponse> posts = quizPostService.getAllQuizPostsWithCommentCount(
			quizBoardId, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(posts);
	}
}