package com.estsoft.guesshangeul.userrank.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.userrank.service.BoardManagerApplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class RankingPageController {
	private final BoardManagerApplyService service;

	@GetMapping("/rank")
	public String showRankingPage() {
		return "ranking";  // templates 폴더의 ranking.html 파일을 반환
	}

	@GetMapping("/boardManagerApply")
	public String getAllBoardManagerApply(Model model) {
		List<BoardManagerApply> list = service.findAll();
		model.addAttribute("list", list);
		return "RankupRequestList";  // RankupRequestList.html 템플릿을 가리킵니다.
	}

	@PostMapping("/newBoardManagerApply")
	public String applyForBoardManager(@AuthenticationPrincipal UserDetails userDetails) {
		// 현재 로그인한 사용자의 ID를 가져옴
		Long userId = Long.parseLong(userDetails.getUsername());

		service.apply(userId);
		//메인 페이지로 이동
		return "redirect:/";
	}
}