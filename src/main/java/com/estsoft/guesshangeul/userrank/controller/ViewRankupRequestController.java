package com.estsoft.guesshangeul.userrank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.userrank.dto.ViewRankupResponse;
import com.estsoft.guesshangeul.userrank.service.ViewRankupRequestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ViewRankupRequestController {
	private final ViewRankupRequestService service;

	@GetMapping("/api/boardManagerApply")//최초 정렬
	public ResponseEntity<List<ViewRankupResponse>> getRankRequest(Model model) {
		List<ViewRankupResponse> list = service.findAll().stream().map(ViewRankupResponse::new).toList();
		return ResponseEntity.ok(list);
	}
}
