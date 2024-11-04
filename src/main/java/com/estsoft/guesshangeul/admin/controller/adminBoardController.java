package com.estsoft.guesshangeul.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estsoft.guesshangeul.admin.service.adminBoardService;
import com.estsoft.guesshangeul.user.dto.usersResponse;

@RequestMapping("/admin/members")
@Controller
public class adminBoardController {
	private adminBoardService adminBoardService;

	@GetMapping
	public ResponseEntity<List<usersResponse>> getAllUsers() {
		List<usersResponse> response = adminBoardService.findAllUsersbyIsDeleted(false);
		return ResponseEntity.ok(response);
	}
}
