package com.estsoft.guesshangeul.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estsoft.guesshangeul.admin.service.AdminBoardService;
import com.estsoft.guesshangeul.user.dto.UsersResponse;

@RequestMapping("/admin/members")
@Controller
public class AdminBoardController {
	private AdminBoardService adminBoardService;

	@GetMapping
	public ResponseEntity<List<UsersResponse>> getAllUsers() {
		List<UsersResponse> response = adminBoardService.findAllUsersbyIsDeleted(false);
		return ResponseEntity.ok(response);
	}
}
