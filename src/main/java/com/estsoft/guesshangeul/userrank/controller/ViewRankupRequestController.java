package com.estsoft.guesshangeul.userrank.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.user.dto.UsersResponse;
import com.estsoft.guesshangeul.userrank.repository.BoardManagerRepository;
import com.estsoft.guesshangeul.userrank.service.ViewRankupRequService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ViewRankupRequestController {//신청 받은 내용을 조회하기 위한 컨트롤러
	private final ViewRankupRequService service;
	private final BoardManagerRepository repository;

	@GetMapping("/api/boardManagerApply")//최초 정렬
	public String getRankRequest(Model model){
		List<UsersResponse> list = service.findAll().stream().map(users -> new UsersResponse(users.getUsers(), "authorityString")).toList();
		model.addAttribute("list", list);
		return "RankupRequestList";
	}
}
