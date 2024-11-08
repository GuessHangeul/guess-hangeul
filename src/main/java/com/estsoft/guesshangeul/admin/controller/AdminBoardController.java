package com.estsoft.guesshangeul.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estsoft.guesshangeul.admin.service.AdminBoardService;
import com.estsoft.guesshangeul.user.dto.UsersResponse;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/user")
@Controller
@RequiredArgsConstructor
public class AdminBoardController {
	private final AdminBoardService adminBoardService;

	@GetMapping
	public ResponseEntity<List<UsersResponse>> getAllUsers() {
		List<UsersResponse> response = adminBoardService.findAllUsersbyIsDeleted(false);
		return ResponseEntity.ok(response);
	}
}