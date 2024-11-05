package com.estsoft.guesshangeul.userrank.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.userrank.dto.AddUserRankRequest;
import com.estsoft.guesshangeul.userrank.entity.boardManagerApply;
import com.estsoft.guesshangeul.userrank.service.UserRankService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRankController {
	private final UserRankService userRankService;

	@PostMapping("/api/boardManagerApply")
	public void rankup(@ModelAttribute AddUserRankRequest request, HttpServletResponse response) {
		boardManagerApply apply = userRankService.saveRequest(request);
	}


}
