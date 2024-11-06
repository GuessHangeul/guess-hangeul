package com.estsoft.guesshangeul.userrank.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.userrank.dto.AddUserRankRequest;
import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.userrank.service.UserRankService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AddUserRankController {
	private final UserRankService userRankService;

	@PostMapping("/api/boardManagerApply")
	public void rankup(@ModelAttribute AddUserRankRequest request) {
		BoardManagerApply apply = userRankService.saveRequest(request);
	}


}
