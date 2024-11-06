package com.estsoft.guesshangeul.userrank.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.userrank.dto.ViewRankupRequResponse;
import com.estsoft.guesshangeul.userrank.service.ViewRankupRequService;

@RestController
public class ViewRankupRequController {
	private final ViewRankupRequService service;

	public ViewRankupRequController(ViewRankupRequService service) {
		this.service = service;
	}

	@GetMapping("/api/boardManagerApply")//최초 정렬
	public String getRankupRequs(Model model){
		List<ViewRankupRequResponse> list = service.findAll().stream().map(ViewRankupRequResponse::new).toList();
		model.addAttribute("list", list);
		return "RankupRequList";
	}
}
