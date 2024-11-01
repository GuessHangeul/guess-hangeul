package com.estsoft.guesshangeul.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.estsoft.guesshangeul.entity.Users;
import com.estsoft.guesshangeul.entity.dto.UsersResponse;
import com.estsoft.guesshangeul.service.admin.AdminService;

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