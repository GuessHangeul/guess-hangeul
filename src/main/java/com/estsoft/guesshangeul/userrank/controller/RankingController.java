package com.estsoft.guesshangeul.userrank.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class RankingController {
	private final UsersService usersService;

	// 점수 기준으로 유저 목록 조회
	@GetMapping
	public List<Users> getRanking(@RequestParam(value = "sort", required = false) String sort) {
		if ("score".equals(sort)) {
			return usersService.getRankedUsers();
		} else {
			// 추가적인 정렬 조건이 필요한 경우 여기에서 처리
			return List.of();
		}
	}
}
