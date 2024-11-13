package com.estsoft.guesshangeul.userrank.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estsoft.guesshangeul.admin.dto.BoardManagerApplyResponse;
import com.estsoft.guesshangeul.userrank.dto.ViewRankupRequestResponse;
import com.estsoft.guesshangeul.userrank.service.ViewRankupRequestService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ViewRankupRequestController {//신청 받은 내용을 조회하기 위한 컨트롤러
	private final ViewRankupRequestService service;

	@GetMapping("/boardManagerApply")//최초 정렬
	public String getBoardManagerApplyRequest(Model model) {
		List<ViewRankupRequestResponse> list = service.findAll()
			.stream()
			.map(boardManagerApply -> new ViewRankupRequestResponse(boardManagerApply, "authorityString"))
			.toList();
		model.addAttribute("list", list);
		return "RankupRequestList";
	}

	@GetMapping("/boardManagerApply/search")//users 닉네임으로 신청찾기
	public String getBoardManagerApplyRequestById(@RequestParam("nickname") String nickname, Model model,
		@PageableDefault(size = 10) Pageable pageable) {
		List<BoardManagerApplyResponse> list = service.findByUsersNickname(nickname, pageable);
		model.addAttribute("list", list);
		return "RankupRequestList";
	}
}
