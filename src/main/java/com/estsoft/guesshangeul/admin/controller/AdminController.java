package com.estsoft.guesshangeul.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.admin.service.AdminService;
import com.estsoft.guesshangeul.post.dto.GeneralPostResponse;
import com.estsoft.guesshangeul.post.dto.QuizPostResponse;
import com.estsoft.guesshangeul.post.entity.GeneralPost;
import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.user.dto.UsersResponse;
import com.estsoft.guesshangeul.user.entity.Users;

@RestController
public class AdminController {
	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping("/api/admin/initializeNickname/{userId}")
	public ResponseEntity<UsersResponse> resetNickname(@PathVariable Long userId) {
		Users users = adminService.resetNickname(userId);
		return ResponseEntity.ok(new UsersResponse(users));
	}

	@GetMapping("/api/admin/generalBoard/{boardId}/post/changeVisibilityHide")
	public ResponseEntity<List<GeneralPostResponse>> changeVisibilityHide(@PathVariable Long boardId,
		@RequestParam List<Long> postId) {
		List<GeneralPost> generalPosts = adminService.generalPostHide(boardId, postId);
		List<GeneralPostResponse> generalPostResponses = new ArrayList<>();
		for (GeneralPost generalPost : generalPosts) {
			generalPostResponses.add(new GeneralPostResponse(generalPost));
		}
		return ResponseEntity.ok(generalPostResponses);
	}

	@GetMapping("/api/admin/generalBoard/{boardId}/post/changeVisibilityUnhidden")
	public ResponseEntity<List<GeneralPostResponse>> changeVisibilityUnhidden(@PathVariable Long boardId,
		@RequestParam List<Long> postId) {
		List<GeneralPost> generalPosts = adminService.generalPostUnhide(boardId, postId);
		List<GeneralPostResponse> generalPostResponses = new ArrayList<>();
		for (GeneralPost generalPost : generalPosts) {
			generalPostResponses.add(new GeneralPostResponse(generalPost));
		}
		return ResponseEntity.ok(generalPostResponses);
	}

	@GetMapping("/api/admin/quizBoard/{boardId}/post/changeVisibilityHide")
	public ResponseEntity<List<QuizPostResponse>> quizChangeVisibilityHide(@PathVariable Long boardId,
		@RequestParam List<Long> postId) {
		List<QuizPost> quizPosts = adminService.quizPostHide(boardId, postId);
		List<QuizPostResponse> quizPostResponses = new ArrayList<>();
		for (QuizPost quizPost : quizPosts) {
			quizPostResponses.add(new QuizPostResponse(quizPost));
		}
		return ResponseEntity.ok(quizPostResponses);
	}

	@GetMapping("/api/admin/quizBoard/{boardId}/post/changeVisibilityUnhidden")
	public ResponseEntity<List<QuizPostResponse>> quizChangeVisibilityUnhidden(@PathVariable Long boardId,
		@RequestParam List<Long> postId) {
		List<QuizPost> quizPosts = adminService.quizPostUnhide(boardId, postId);
		List<QuizPostResponse> quizPostResponses = new ArrayList<>();
		for (QuizPost quizPost : quizPosts) {
			quizPostResponses.add(new QuizPostResponse(quizPost));
		}
		return ResponseEntity.ok(quizPostResponses);
	}

}