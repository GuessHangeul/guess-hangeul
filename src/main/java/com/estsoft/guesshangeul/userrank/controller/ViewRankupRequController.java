package com.estsoft.guesshangeul.userrank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.userrank.dto.ViewRankupRequResponse;
import com.estsoft.guesshangeul.userrank.service.ViewRankupRequService;

@RestController
public class ViewRankupRequController {//신청 받은 내용을 조회하기 위한 컨트롤러
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
	@PostMapping("/api/boardManagerApply")//허가/불허 처리
	public ResponseEntity<Map<String,Object>> updateStatus(@RequestBody Status)
}
