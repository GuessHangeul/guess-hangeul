package com.estsoft.guesshangeul.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "관리자 페이지 컨트롤러")
public class AdminController {
	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	// 유저 닉네임 초기화
	@PutMapping("/api/admin/initializeNickname/{userId}")
	@Parameter(name = "userId", description = "유저 ID", example = "1")
	@Operation(summary = "유저 닉네임 초기화", description = "관리자 페이지 유저 관리 중 유저 닉네임 초기화 기능")
	public ResponseEntity<UsersResponse> resetNickname(@PathVariable Long userId) {
		Users users = adminService.resetNickname(userId);
		return ResponseEntity.ok(new UsersResponse(users));
	}

	// 일반 게시판 삭제
	@PutMapping("/api/admin/deleteBoard/generalBoard/{boardId}")
	@Parameter(name = "boardId", description = "게시판ID", example = "1")
	@Operation(summary = "일반 게시판 삭제", description = "관리자 페이지 일반 게시판 관리 중 게시판 삭제 기능")
	public ResponseEntity<GeneralBoardResponse> deleteGeneralBoard(@PathVariable Long boardId) {
		GeneralBoard generalBoard = adminService.deleteGeneralBoard(boardId);
		return ResponseEntity.ok(new GeneralBoardResponse(generalBoard));
	}

	// 퀴즈 게시판 삭제
	@PutMapping("/api/admin/deleteBoard/quizBoard/{boardId}")
	@Parameter(name = "boardId", description = "게시판ID", example = "1")
	@Operation(summary = "퀴즈 게시판 삭제", description = "관리자 페이지 퀴즈 게시판 관리 중 게시판 삭제 기능")
	public ResponseEntity<QuizBoardResponse> deleteQuizBoard(@PathVariable Long boardId) {
		QuizBoard quizBoard = adminService.deleteQuizBoard(boardId);
		return ResponseEntity.ok(new QuizBoardResponse(quizBoard));
	}

	// 일반 게시판 게시글 숨김
	@PutMapping("/api/admin/generalBoard/{boardId}/post/changeVisibilityHide")
	@Operation(summary = "일반 게시글 숨김", description = "관리자 페이지 일반 게시판 게시글 숨김")
	public ResponseEntity<List<GeneralPostResponse>> changeVisibilityHide(@PathVariable Long boardId,
		@RequestBody List<Long> postId) {
		List<GeneralPost> generalPosts = adminService.generalPostHide(boardId, postId);
		List<GeneralPostResponse> generalPostResponses = new ArrayList<>();
		for (GeneralPost generalPost : generalPosts) {
			generalPostResponses.add(new GeneralPostResponse(generalPost));
		}
		return ResponseEntity.ok(generalPostResponses);
	}

	// 일반 게시판 게시글 숨김 해제
	@PutMapping("/api/admin/generalBoard/{boardId}/post/changeVisibilityUnhidden")
	@Operation(summary = "일반 게시글 숨김 해제", description = "관리자 페이지 일반 게시판 게시글 숨김 해제")
	public ResponseEntity<List<GeneralPostResponse>> changeVisibilityUnhidden(@PathVariable Long boardId,
		@RequestBody List<Long> postId) {
		List<GeneralPost> generalPosts = adminService.generalPostUnhide(boardId, postId);
		List<GeneralPostResponse> generalPostResponses = new ArrayList<>();
		for (GeneralPost generalPost : generalPosts) {
			generalPostResponses.add(new GeneralPostResponse(generalPost));
		}
		return ResponseEntity.ok(generalPostResponses);
	}

	// 퀴즈 게시판 게시글 숨김
	@PutMapping("/api/admin/quizBoard/{boardId}/post/changeVisibilityHide")
	@Operation(summary = "퀴즈 게시글 숨김", description = "관리자 페이지 퀴즈 게시판 게시글 숨김")
	public ResponseEntity<List<QuizPostResponse>> quizChangeVisibilityHide(@PathVariable Long boardId,
		@RequestBody List<Long> postId) {
		List<QuizPost> quizPosts = adminService.quizPostHide(boardId, postId);
		List<QuizPostResponse> quizPostResponses = new ArrayList<>();
		for (QuizPost quizPost : quizPosts) {
			quizPostResponses.add(new QuizPostResponse(quizPost));
		}
		return ResponseEntity.ok(quizPostResponses);
	}

	// 퀴즈 게시판 게시글 숨김 해제
	@Operation(summary = "퀴즈 게시글 숨김 해제", description = "관리자 페이지 퀴즈 게시판 게시글 숨김 해제")
	@PutMapping("/api/admin/quizBoard/{boardId}/post/changeVisibilityUnhidden")
	public ResponseEntity<List<QuizPostResponse>> quizChangeVisibilityUnhidden(@PathVariable Long boardId,
		@RequestBody List<Long> postId) {
		List<QuizPost> quizPosts = adminService.quizPostUnhide(boardId, postId);
		List<QuizPostResponse> quizPostResponses = new ArrayList<>();
		for (QuizPost quizPost : quizPosts) {
			quizPostResponses.add(new QuizPostResponse(quizPost));
		}
		return ResponseEntity.ok(quizPostResponses);
	}

	// 집현전 신청 승인
	@PutMapping("/api/admin/acceptBoardManager")
	@Operation(summary = "집현전 신청 승인", description = "관리자 집현전 관리 페이지 신청 승인")
	public ResponseEntity<BoardManagerApplyResponse> acceptBoardManager(@RequestBody Long boardManagerId) {
		BoardManagerApply boardManagerApply = adminService.acceptBoardManager(boardManagerId);
		return ResponseEntity.ok(new BoardManagerApplyResponse(boardManagerApply));
	}

	// 집현전 신청 거부
	@PutMapping("/api/admin/rejectBoardManager")
	@Operation(summary = "집현전 신청 거부", description = "관리자 집현전 관리 페이지 신청 거부")
	public ResponseEntity<BoardManagerApplyResponse> rejectBoardManager(@RequestBody Long boardManagerId) {
		BoardManagerApply boardManagerApply = adminService.rejectBoardManager(boardManagerId);
		return ResponseEntity.ok(new BoardManagerApplyResponse(boardManagerApply));
	}

}