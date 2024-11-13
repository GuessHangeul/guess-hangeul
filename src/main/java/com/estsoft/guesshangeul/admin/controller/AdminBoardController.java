package com.estsoft.guesshangeul.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estsoft.guesshangeul.admin.service.AdminBoardService;
import com.estsoft.guesshangeul.user.dto.UsersResponse;
import com.estsoft.guesshangeul.user.service.UsersService;

import lombok.RequiredArgsConstructor;

@RequestMapping(value = "/api/user")
@Controller
@RequiredArgsConstructor
public class AdminBoardController {
	private final AdminBoardService adminBoardService;
	private final UsersService usersService;

	@GetMapping(value="/api/user")
	public ResponseEntity<List<UsersResponse>> getAllUsers() {
		List<UsersResponse> response = adminBoardService.findAllUsersbyIsDeleted(false);
		List<UsersResponse> usersResponses = new ArrayList<>();
		for (UsersResponse user : response) {
			usersResponses.add(usersService.getUserResponse(user.getUserId()));
		}
		return ResponseEntity.ok(usersResponses);
	}
}
