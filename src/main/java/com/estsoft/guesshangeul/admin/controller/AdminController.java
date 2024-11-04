package com.estsoft.guesshangeul.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.admin.service.AdminService;
import com.estsoft.guesshangeul.user.dto.usersResponse;
import com.estsoft.guesshangeul.user.entity.Users;

@RestController
public class AdminController {
	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping("/api/admin/initializeNickname/{userId}")
	public ResponseEntity<usersResponse> resetNickname(@PathVariable Long userId) {
		Users users = adminService.resetNickname(userId);
		return ResponseEntity.ok(new usersResponse(users));
	}

}