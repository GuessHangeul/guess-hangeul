package com.estsoft.guesshangeul.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.admin.dto.BoardManagerApplyResponse;
import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.admin.service.AdminService;
import com.estsoft.guesshangeul.board.dto.GeneralBoardResponse;
import com.estsoft.guesshangeul.board.dto.QuizBoardResponse;
import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.board.entity.QuizBoard;
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

	// 유저 닉네임 초기화
	@PutMapping("/api/admin/initializeNickname/{userId}")
	public ResponseEntity<UsersResponse> resetNickname(@PathVariable Long userId) {
		Users users = adminService.resetNickname(userId);
		return ResponseEntity.ok(new UsersResponse(users));
	}

	// 일반 게시판 삭제
	@PutMapping("/api/admin/deleteBoard/generalBoard/{boardId}")
	public ResponseEntity<GeneralBoardResponse> deleteGeneralBoard(@PathVariable Long boardId) {
		GeneralBoard generalBoard = adminService.deleteGeneralBoard(boardId);
		return ResponseEntity.ok(new GeneralBoardResponse(generalBoard));
	}

	// 퀴즈 게시판 삭제
	@PutMapping("/api/admin/deleteBoard/quizBoard/{boardId}")
	public ResponseEntity<QuizBoardResponse> deleteQuizBoard(@PathVariable Long boardId) {
		QuizBoard quizBoard = adminService.deleteQuizBoard(boardId);
		return ResponseEntity.ok(new QuizBoardResponse(quizBoard));
	}

	// 일반 게시판 게시글 숨김
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

	// 일반 게시판 게시글 숨김 해제
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

	// 퀴즈 게시판 게시글 숨김
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

	// 퀴즈 게시판 게시글 숨김 해제
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

	// 집현전 신청 승인
	@GetMapping("/api/admin/acceptBoardManager")
	public ResponseEntity<BoardManagerApplyResponse> acceptBoardManager(@RequestParam Long boardManagerId) {
		BoardManagerApply boardManagerApply = adminService.acceptBoardManager(boardManagerId);
		return ResponseEntity.ok(new BoardManagerApplyResponse(boardManagerApply));
	}

	// 집현전 신청 거부
	@GetMapping("/api/admin/rejectBoardManager")
	public ResponseEntity<BoardManagerApplyResponse> rejectBoardManager(@RequestParam Long boardManagerId) {
		BoardManagerApply boardManagerApply = adminService.rejectBoardManager(boardManagerId);
		return ResponseEntity.ok(new BoardManagerApplyResponse(boardManagerApply));
	}

}