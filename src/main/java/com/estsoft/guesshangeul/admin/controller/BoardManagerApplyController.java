package com.estsoft.guesshangeul.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.userrank.service.BoardManagerApplyService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardManagerApplyController {
	private final BoardManagerApplyService service;

	@GetMapping("/boardManagerApply")
	public String getAllBoardManagerApply(Model model) {
		List<BoardManagerApply> list = service.findAll();
		model.addAttribute("list", list);
		return "RankupRequestList";  // RankupRequestList.html 템플릿을 가리킵니다.
	}
}
