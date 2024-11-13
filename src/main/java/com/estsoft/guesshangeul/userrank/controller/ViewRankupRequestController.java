package com.estsoft.guesshangeul.userrank.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.user.service.UsersService;
import com.estsoft.guesshangeul.userrank.dto.ViewRankupResponse;
import com.estsoft.guesshangeul.userrank.service.ViewRankupRequestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ViewRankupRequestController {
	private final ViewRankupRequestService service;
	private final UsersService usersService;

	@GetMapping("/api/boardManagerApply")
	public ResponseEntity<List<ViewRankupResponse>> getRankRequest() {
		List<BoardManagerApply> responseList = service.findAll();
		List<ViewRankupResponse> list = new ArrayList<>();
		for (BoardManagerApply boardManagerApply : responseList) {
			list.add(usersService.getViewRankupResponse(boardManagerApply.getUsers().getId()));
		}
		return ResponseEntity.ok(list);
	}
}
