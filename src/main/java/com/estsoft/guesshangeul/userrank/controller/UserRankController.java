package com.estsoft.guesshangeul.userrank.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.user.dto.AddAuthorityRequest;
import com.estsoft.guesshangeul.user.dto.AddUserRequest;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.userrank.dto.AddUserRankRequest;
import com.estsoft.guesshangeul.userrank.entity.boardManagerApply;
import com.estsoft.guesshangeul.userrank.service.UserRankService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructorS
public class UserRankController {
	private final UserRankService userRankService;

	public UserRankController(UserRankService userRankService)
	{this.userRankService = userRankService;}


	@PostMapping("/api/boardManagerApply")
	public void rankup(@ModelAttribute AddUserRankRequest request, HttpServletResponse response) {
		boardManagerApply apply = userRankService.saveRequest(request);
	}


}
