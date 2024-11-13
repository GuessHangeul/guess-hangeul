package com.estsoft.guesshangeul.userrank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.userrank.dto.AddUserRankRequest;
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
}
