package com.estsoft.guesshangeul.userrank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.userrank.dto.ViewRankupRequestResponse;
import com.estsoft.guesshangeul.userrank.service.BoardManagerApplyService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class AddUserRankController {//신청 내용 저장을 위한 컨트롤러
	private final BoardManagerApplyService boardManagerApplyService;

	public AddUserRankController(BoardManagerApplyService boardManagerApplyService) {
		this.boardManagerApplyService = boardManagerApplyService;
	}

	@Operation(summary = "집현전 신청", description = "집현전 신청")
	@PostMapping("/boardManagerApply")
	public ResponseEntity<ViewRankupRequestResponse> addBoardManager() {
		ViewRankupRequestResponse response = boardManagerApplyService.addBoardManager();
		return ResponseEntity.ok(response);
	}
}
