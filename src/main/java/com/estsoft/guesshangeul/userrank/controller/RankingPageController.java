package com.estsoft.guesshangeul.userrank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.estsoft.guesshangeul.userrank.service.BoardManagerApplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class RankingPageController {
	private final BoardManagerApplyService service;
	private final BoardManagerApplyService boardManagerApplyService;

	@GetMapping("/rank")
	public String showRankingPage() {
		return "ranking";  // templates 폴더의 ranking.html 파일을 반환
	}

	@GetMapping("/newBoardManagerApply")
	public String applyBoardManager() {
		return "NewBoardManagerApply";
	}
}