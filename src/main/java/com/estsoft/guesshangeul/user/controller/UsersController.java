package com.estsoft.guesshangeul.user.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.user.dto.AddAuthorityRequest;
import com.estsoft.guesshangeul.user.dto.AddAuthorityResponse;
import com.estsoft.guesshangeul.user.dto.AddUserRequest;
import com.estsoft.guesshangeul.user.dto.CheckEmailExistsRequest;
import com.estsoft.guesshangeul.user.dto.CheckEmailExistsResponse;
import com.estsoft.guesshangeul.user.dto.CheckNicknameExistsRequest;
import com.estsoft.guesshangeul.user.dto.CheckNicknameExistsResponse;
import com.estsoft.guesshangeul.user.entity.Authorities;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.service.UsersDetailsService;
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
	private final UsersDetailsService usersDetailsService;

	// 회원 가입
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

	// 권한 추가
	@PostMapping("/authority")
	public ResponseEntity<List<AddAuthorityResponse>> addAuthority(
		@RequestBody List<AddAuthorityRequest> addAuthorityRequestList) {
		List<Authorities> authorities = usersDetailsService.saveAuthorities(addAuthorityRequestList);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(authorities.stream()
				.map(Authority -> new AddAuthorityResponse(
					Authority.getId(), Authority.getUserId(), Authority.getAuthority()
				))
				.toList());
	}

	@PostMapping("/checkEmailDuplicate")
	public ResponseEntity<CheckEmailExistsResponse> checkEmailExists(@RequestBody CheckEmailExistsRequest request) {
		String email = request.getEmail();
		Boolean result = usersService.checkEmailExists(email);
		String message = result ? "이메일이 존재합니다." : "존재하지 않는 이메일입니다.";
		CheckEmailExistsResponse response = new CheckEmailExistsResponse(email, result, message);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/checkNicknameDuplicate")
	public ResponseEntity<CheckNicknameExistsResponse> checkNicknameExists(
		@RequestBody CheckNicknameExistsRequest request) {
		String nickname = request.getNickname();
		Boolean result = usersService.checkNicknameExists(nickname);
		String message = result ? "닉네임이 존재합니다." : "존재하지 않는 닉네임입니다.";
		CheckNicknameExistsResponse response = new CheckNicknameExistsResponse(nickname, result, message);
		return ResponseEntity.ok(response);
	}
}
