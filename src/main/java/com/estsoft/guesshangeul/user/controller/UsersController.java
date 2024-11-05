package com.estsoft.guesshangeul.user.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.user.dto.AddAuthorityRequest;
import com.estsoft.guesshangeul.user.dto.AddAuthorityResponse;
import com.estsoft.guesshangeul.user.dto.AddUserRequest;
import com.estsoft.guesshangeul.user.entity.Authorities;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.service.UsersService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsersController {
	private final UsersService usersService;

	@PostMapping("/signup")
	public void signup(@ModelAttribute AddUserRequest request, HttpServletResponse response) {
		Users users = usersService.save(request);
		List<AddAuthorityRequest> addAuthorityRequestList = new ArrayList<>();
		addAuthorityRequestList.add(new AddAuthorityRequest(users.getId(), "ROLE_NOBI"));
		addAuthority(addAuthorityRequestList);
		try {
			response.sendRedirect("/login");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	@PostMapping("/authority")
	public ResponseEntity<List<AddAuthorityResponse>> addAuthority(
		@RequestBody List<AddAuthorityRequest> addAuthorityRequestList) {
		List<Authorities> authorities = usersService.saveAuthorities(addAuthorityRequestList);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(authorities.stream()
				.map(Authority -> new AddAuthorityResponse(
					Authority.getId(), Authority.getUserId(), Authority.getAuthority()
				))
				.toList());
	}
}
