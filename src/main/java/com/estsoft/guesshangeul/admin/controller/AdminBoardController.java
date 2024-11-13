package com.estsoft.guesshangeul.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estsoft.guesshangeul.admin.service.AdminBoardService;
import com.estsoft.guesshangeul.user.dto.UsersResponse;
import com.estsoft.guesshangeul.user.service.UsersService;

import lombok.RequiredArgsConstructor;

@RequestMapping
@Controller
@RequiredArgsConstructor
public class AdminBoardController {
	private final AdminBoardService adminBoardService;
	private final UsersService usersService;

	@GetMapping("/api/user")
	public ResponseEntity<List<UsersResponse>> getAllUsers(
		@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
		@RequestParam(value = "size", required = false) Integer size,
		@RequestParam(value = "sort", required = false) String sort) {
		int pageSize = (size == null) ? Integer.MAX_VALUE : size;

		Pageable pageable;

		if (sort == null) {
			pageable = PageRequest.of(page, pageSize);
		} else {
			String[] sortParams = sort.split(",");
			String sortField = sortParams[0];
			Sort.Direction direction = sortParams.length > 1 && "asc".equalsIgnoreCase(sortParams[1])
				? Sort.Direction.ASC : Sort.Direction.DESC;  // 정렬 방향
			pageable = PageRequest.of(page, pageSize, Sort.by(direction, sortField));
		}

		List<UsersResponse> response = adminBoardService.findAllUsersbyIsDeleted(false, pageable);
		List<UsersResponse> usersResponses = new ArrayList<>();
		for (UsersResponse user : response) {
			usersResponses.add(usersService.getUserResponse(user.getUserId()));
		}
		return ResponseEntity.ok(usersResponses);
	}
}
