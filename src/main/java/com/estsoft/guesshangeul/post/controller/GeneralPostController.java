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
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.post.dto.AddGeneralPostRequest;
import com.estsoft.guesshangeul.post.dto.GeneralPostResponse;
import com.estsoft.guesshangeul.post.dto.GetHiddenPostResponse;
import com.estsoft.guesshangeul.post.dto.UpdateGeneralPostRequest;
import com.estsoft.guesshangeul.post.service.GeneralPostService;

@RestController
@RequestMapping("/api/generalBoard/{generalBoardId}/generalPost")
public class GeneralPostController {

	private final GeneralPostService generalPostService;

	public GeneralPostController(GeneralPostService generalPostService) {
		this.generalPostService = generalPostService;
	}

	// 전체 게시글 조회
	@GetMapping
	public ResponseEntity<List<GeneralPostResponse>> getAllGeneralPosts(@PathVariable Long generalBoardId) {
		List<GeneralPostResponse> posts = generalPostService.getAllGeneralPosts(generalBoardId);
		return ResponseEntity.status(HttpStatus.OK).body(posts);
	}

	// 게시글 id로 조회
	@GetMapping("/{general_post_id}")
	public ResponseEntity<GeneralPostResponse> getGeneralPostById(@PathVariable Long general_post_id) {
		GeneralPostResponse post = generalPostService.getGeneralPostById(general_post_id);
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}

	// 게시글 제목으로 조회
	@GetMapping("?search={title}")
	public ResponseEntity<GeneralPostResponse> getGeneralPostByTitle(@PathVariable String title) {
		GeneralPostResponse post = generalPostService.getGeneralPostByTitle(title);
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}

	// 게시글 생성
	@PostMapping
	public ResponseEntity<GeneralPostResponse> createGeneralPost(@RequestBody AddGeneralPostRequest request) {
		GeneralPostResponse post = generalPostService.createGeneralPost(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(post);
	}

	// 게시글 수정
	@PutMapping("/{general_post_id}")
	public ResponseEntity<GeneralPostResponse> updateGeneralPost(@PathVariable Long general_post_id,
		@RequestBody UpdateGeneralPostRequest request) {
		GeneralPostResponse post = generalPostService.updateGeneralPost(general_post_id, request);
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}

	// 게시글 삭제
	@DeleteMapping("/{general_post_id}")
	public ResponseEntity<Void> deleteGeneralPost(@PathVariable Long general_post_id) {
		generalPostService.deleteGeneralPost(general_post_id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}