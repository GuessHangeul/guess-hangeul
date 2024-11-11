package com.estsoft.guesshangeul.post.controller;

import java.util.List;

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
import com.estsoft.guesshangeul.post.dto.GetHiddenPostResponse;
import com.estsoft.guesshangeul.post.dto.QuizPostResponse;
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

	// 전체 퀴즈 게시글 조회
	@GetMapping
	public ResponseEntity<List<QuizPostResponse>> getAllQuizPosts(@PathVariable Long quizBoardId) {
		List<QuizPostResponse> posts = quizPostService.getAllQuizPosts(quizBoardId);
		return ResponseEntity.status(HttpStatus.OK).body(posts);
	}

	// 퀴즈 게시글 id로 조회
	@GetMapping("/{quiz_post_id}")
	public ResponseEntity<QuizPostResponse> getQuizPostById(@PathVariable Long quiz_post_id) {
		QuizPostResponse post = quizPostService.getQuizPostById(quiz_post_id);
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}

	// 퀴즈 게시글 제목으로 조회
	@GetMapping("?search={quiz_title}")
	public ResponseEntity<QuizPostResponse> getQuizPostByTitle(@PathVariable String quiz_title) {
		QuizPostResponse post = quizPostService.getQuizPostByTitle(quiz_title);
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}

	// 퀴즈 게시글 숨김 여부 조회
	@GetMapping("?isHidden={isHidden}")
	public ResponseEntity<List<GetHiddenPostResponse>> getQuizPostByIsHidden(@RequestParam boolean isHidden) {
		List<GetHiddenPostResponse> posts = quizPostService.getQuizPostByIsHidden(isHidden);
		return ResponseEntity.status(HttpStatus.OK).body(posts);
	}

	// 퀴즈 게시글 생성
	@PostMapping
	public ResponseEntity<QuizPostResponse> createQuizPost(@RequestBody AddQuizPostRequest request) {
		QuizPostResponse post = quizPostService.createQuizPost(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(post);
	}

	// 퀴즈 게시글 수정
	@PutMapping("/{quiz_post_id}")
	public ResponseEntity<QuizPostResponse> updateQuizPost(@PathVariable Long quiz_post_id,
		@RequestBody UpdateQuizPostRequest request) {
		QuizPostResponse post = quizPostService.updateQuizPost(quiz_post_id, request);
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}

	// 퀴즈 게시글 삭제
	@DeleteMapping("/{quiz_post_id}")
	public ResponseEntity<Void> deleteQuizPost(@PathVariable Long quiz_post_id) {
		quizPostService.deleteQuizPost(quiz_post_id);
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
