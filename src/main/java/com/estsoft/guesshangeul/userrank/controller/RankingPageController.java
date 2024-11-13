package com.estsoft.guesshangeul.userrank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RankingPageController {

	@GetMapping("/rank")
	public String showRankingPage() {
		return "ranking";  // templates 폴더의 ranking.html 파일을 반환
	}
}