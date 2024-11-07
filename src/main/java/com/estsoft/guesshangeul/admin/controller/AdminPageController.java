package com.estsoft.guesshangeul.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.estsoft.guesshangeul.admin.service.AdminBoardService;
import com.estsoft.guesshangeul.user.dto.UsersResponse;

@Controller
public class AdminPageController {

	private final AdminBoardService adminBoardService;

	public AdminPageController(AdminBoardService adminBoardService) {
		this.adminBoardService = adminBoardService;
	}

	@GetMapping("/admin")
	public String showUser(Model model) {
		List<UsersResponse> usersResponses = adminBoardService.findAllUsersbyIsDeleted(false);

		model.addAttribute("users", usersResponses);

		return "admin";
	}
}
