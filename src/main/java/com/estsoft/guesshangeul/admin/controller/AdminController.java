package com.estsoft.guesshangeul.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.admin.service.AdminService;
import com.estsoft.guesshangeul.board.dto.GeneralBoardResponse;
import com.estsoft.guesshangeul.board.dto.QuizBoardResponse;
import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.board.entity.QuizBoard;
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

	@GetMapping("/api/admin/deleteBoard/generalBoard/{boardId}")
	public ResponseEntity<GeneralBoardResponse> deleteGeneralBoard(@PathVariable Long boardId) {
		GeneralBoard generalBoard = adminService.deleteGeneralBoard(boardId);
		return ResponseEntity.ok(new GeneralBoardResponse(generalBoard));
	}

	@GetMapping("/api/admin/deleteBoard/quizBoard/{boardId}")
	public ResponseEntity<QuizBoardResponse> deleteQuizBoard(@PathVariable Long boardId) {
		QuizBoard quizBoard = adminService.deleteQuizBoard(boardId);
		return ResponseEntity.ok(new QuizBoardResponse(quizBoard));
	}

}