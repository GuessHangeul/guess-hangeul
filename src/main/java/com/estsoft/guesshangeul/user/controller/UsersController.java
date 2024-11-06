package com.estsoft.guesshangeul.user.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.user.dto.AddAuthorityRequest;
import com.estsoft.guesshangeul.user.dto.AddAuthorityResponse;
import com.estsoft.guesshangeul.user.dto.AddUserRequest;
import com.estsoft.guesshangeul.user.dto.ModifyPwdRequest;
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
	@PostMapping("/user/authority")
	public ResponseEntity<List<AddAuthorityResponse>> addAuthority(
		@RequestBody List<AddAuthorityRequest> addAuthorityRequestList) {
		List<Authorities> authorities = usersDetailsService.saveUserAuthorities(addAuthorityRequestList);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(authorities.stream()
				.map(Authority -> new AddAuthorityResponse(
					Authority.getId(), Authority.getUserId(), Authority.getAuthority()
				))
				.toList());
	}

	// 비밀번호 변경 메일
	@PostMapping("/resetPasswordRequest/{email}")
	public ResponseEntity<String> resetPassword(@PathVariable String email) {
		Users user = usersService.findUserByEmail(email);

		usersService.createPasswordResetTokenForUser(user);
		return ResponseEntity.ok("비밀번호 재설정 링크가 이메일로 발송되었습니다.");
	}

	// 비밀번호 변경
	@PutMapping("/resetPassword")
	public ResponseEntity<String> resetPassword(@RequestBody ModifyPwdRequest request) {
		String result = usersService.validatePasswordResetToken(request.getToken());
		if (!result.equals("valid")) {
			return ResponseEntity.badRequest().body("토큰이 유효하지 않습니다.");
		}

		usersService.changePassword(request.getToken(), request.getPassword());
		return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
	}

}
