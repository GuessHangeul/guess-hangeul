package com.estsoft.guesshangeul.userrank.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.userrank.dto.AddUserRankRequest;
import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.userrank.service.BoardManagerApplyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class AddUserRankController {//신청 내용 저장을 위한 컨트롤러
	private final BoardManagerApplyService service;

	public AddUserRankController(BoardManagerApplyService service) {
		this.service = service;
	}

	@PostMapping("/boardManagerApply")
	public ResponseEntity<BoardManagerApply> addUserRankupRequest(@RequestBody AddUserRankRequest request) {
		BoardManagerApply rankuprequest = service.save(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(rankuprequest);
	}

	@PostMapping("/newBoardManagerApply")
	public String applyForBoardManager(@AuthenticationPrincipal UserDetails userDetails) {
		// 현재 로그인한 사용자의 ID를 가져옴
		Long userId = Long.parseLong(userDetails.getUsername());

		service.apply(userId);
		//메인 페이지로 이동
		return "redirect:/";
	}
	@GetMapping("/boardManagerApply")
	public String getAllBoardManagerApply(Model model) {
		List<BoardManagerApply> list = service.findAll();
		model.addAttribute("list", list);
		return "RankupRequestList";  // RankupRequestList.html 템플릿을 가리킵니다.
	}
}
