package com.estsoft.guesshangeul.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.estsoft.guesshangeul.admin.service.AdminService;
import com.estsoft.guesshangeul.user.dto.UsersResponse;
import com.estsoft.guesshangeul.user.entity.Users;

@Controller
public class AdminController {
	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping("/api/admin/initializeNickname/{user_id}")
	public ResponseEntity<UsersResponse> resetNickname(@PathVariable Long user_id) {
		Users users = adminService.resetNickname(user_id);
		return ResponseEntity.ok(new UsersResponse(users));
	}

}