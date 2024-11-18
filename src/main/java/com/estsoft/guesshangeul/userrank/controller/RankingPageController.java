package com.estsoft.guesshangeul.userrank.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.service.UsersDetailsService;
import com.estsoft.guesshangeul.userrank.service.BoardManagerApplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class RankingPageController {
	private final BoardManagerApplyService service;
	private final BoardManagerApplyService boardManagerApplyService;
	private final UsersDetailsService usersDetailsService;

	@GetMapping("/rank")
	public String showRankingPage() {
		return "ranking";  // templates 폴더의 ranking.html 파일을 반환
	}

	@GetMapping("/newBoardManagerApply")
	public String applyBoardManager() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username;
		if (authentication.getPrincipal() instanceof UserDetails userDetails) {
			username = userDetails.getUsername();
		} else {
			username = (String)authentication.getPrincipal();
		}

		Users users = (Users)usersDetailsService.loadUserByUsername(username);
		List<BoardManagerApply> responseList = service.findAll();

		for (BoardManagerApply boardManagerApply : responseList) {
			if (boardManagerApply.getUsers().getId() == users.getId()) {
				return "NoBoardManager";
			}
		}
		return "NewBoardManagerApply";
	}
}