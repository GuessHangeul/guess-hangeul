package com.estsoft.guesshangeul.user.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.guesshangeul.user.dto.AddAuthorityRequest;
import com.estsoft.guesshangeul.user.dto.AddUserRequest;
import com.estsoft.guesshangeul.user.dto.AuthorityResponse;
import com.estsoft.guesshangeul.user.dto.CheckEmailExistsRequest;
import com.estsoft.guesshangeul.user.dto.CheckEmailExistsResponse;
import com.estsoft.guesshangeul.user.dto.CheckNicknameExistsRequest;
import com.estsoft.guesshangeul.user.dto.CheckNicknameExistsResponse;
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
	public void signup(@RequestBody AddUserRequest request) {
		Users users = usersService.save(request);
		List<AddAuthorityRequest> addAuthorityRequestList = new ArrayList<>();
		addAuthorityRequestList.add(new AddAuthorityRequest(users.getId(), "ROLE_NOBI"));
		addAuthority(addAuthorityRequestList);
	}

	// 권한 추가
	@PostMapping("/user/authority")
	public ResponseEntity<List<AuthorityResponse>> addAuthority(
		@RequestBody List<AddAuthorityRequest> addAuthorityRequestList) {
		List<Authorities> authorities = usersDetailsService.saveUserAuthorities(addAuthorityRequestList);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(authorities.stream()
				.map(Authority -> new AuthorityResponse(Authority.getId(), Authority.getUserId(),
					Authority.getAuthority()))
				.toList());
	}

	//권한 조회
	@GetMapping("/user/findAuthority/{userId}")
	public ResponseEntity<List<AuthorityResponse>> findAuthority(@PathVariable Long userId) {
		List<GrantedAuthority> grantedAuthorityList = usersDetailsService.loadUserAuthorities(userId);
		List<AuthorityResponse> authorityResponseList = new ArrayList<>();

		grantedAuthorityList.forEach(
			grantedAuthority -> authorityResponseList.add(new AuthorityResponse(grantedAuthority.getAuthority()))
		);
		return ResponseEntity.ok(authorityResponseList);
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

	// 비밀번호 변경 메일
	@PostMapping("/resetPasswordRequest/{email}")
	public ResponseEntity<Map<String, String>> createTokenSendEmail(@PathVariable String email) {
		Map<String, String> response = new HashMap<>();

		try {
			Users user = usersService.findUserByEmail(email);
			usersService.createTokenSendEmail(user);
			response.put("status", "success");
			response.put("message", "비밀번호 재설정 이메일이 발송되었습니다.");
		} catch (Exception e) {
			response.put("status", "error");
			response.put("message", "이메일 발송 중 오류가 발생했습니다.");
		}

		return ResponseEntity.ok(response);
	}

	// 비밀번호 변경
	@PostMapping("/resetPassword")
	public void resetPassword(@ModelAttribute("token") ModifyPwdRequest request, HttpServletResponse response) {
		usersService.changePassword(request.getToken(), request.getPassword());
		try {
			response.sendRedirect("/");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

}
