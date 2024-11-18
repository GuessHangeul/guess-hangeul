package com.estsoft.guesshangeul.post.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.post.dto.AddQuizPostRequest;
import com.estsoft.guesshangeul.post.dto.QuizPostResponse;
import com.estsoft.guesshangeul.post.dto.QuizPostWithCommentCountResponse;
import com.estsoft.guesshangeul.post.dto.UpdateQuizPostRequest;
import com.estsoft.guesshangeul.post.service.QuizPostService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/quizBoard/{quizBoardId}/quizPost")
public class QuizPostController {
	private final QuizPostService quizPostService;

	public QuizPostController(QuizPostService quizPostService) {
		this.quizPostService = quizPostService;
	}

	// 전체 게시글 조회
	@GetMapping
	public ResponseEntity<List<QuizPostWithCommentCountResponse>> getAllQuizPostsWithCommentCount(
		@PathVariable Long quizBoardId,
		@RequestParam(value = "search", required = false) String title,
		@RequestParam(value = "isHidden", required = false) Boolean isHidden,
		@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
		List<QuizPostWithCommentCountResponse> posts;
		if (title == null) {
			posts = quizPostService.getAllQuizPostsWithCommentCount(quizBoardId, isHidden, pageable);
		} else {
			// 제목 검색으로 조회
			posts = quizPostService.getAllQuizPostsByTitleWithCommentCount(quizBoardId, title, isHidden, pageable);
		}

		return ResponseEntity.status(HttpStatus.OK).body(posts);
	}

	// 퀴즈 게시글 id로 조회
	@GetMapping("/{id}")
	public ResponseEntity<QuizPostResponse> getQuizPostById(@PathVariable Long quizBoardId, @PathVariable Long id) {
		QuizPostResponse post = quizPostService.getQuizPostById(id);
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}

	// 퀴즈 게시글 생성
	@PostMapping
	public ResponseEntity<QuizPostResponse> createQuizPost(@RequestBody AddQuizPostRequest request,
		@PathVariable Long quizBoardId) {
		QuizPostResponse post = quizPostService.createQuizPost(request, quizBoardId);
		return ResponseEntity.status(HttpStatus.CREATED).body(post);
	}

	// 퀴즈 게시글 수정
	@PutMapping("/{id}")
	public ResponseEntity<QuizPostResponse> updateQuizPost(@PathVariable Long quizBoardId, @PathVariable Long id,
		@RequestBody UpdateQuizPostRequest request) {
		QuizPostResponse post = quizPostService.updateQuizPost(id, request);
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}

	// 퀴즈 게시글 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteQuizPost(@PathVariable Long quizBoardId, @PathVariable Long id) {
		quizPostService.deleteQuizPost(quizBoardId, id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	// 퀴즈 게시글 삭제
	@Transactional
	@DeleteMapping
	public ResponseEntity<Void> deleteByPostIds(@PathVariable Long quizBoardId, @RequestParam List<Long> postId) {
		quizPostService.deleteQuizPostByIdIn(quizBoardId, postId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
