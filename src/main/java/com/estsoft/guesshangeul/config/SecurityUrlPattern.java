package com.estsoft.guesshangeul.config;

import org.springframework.http.HttpMethod;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SecurityUrlPattern {
	// 로그인 하지 않은 사용자
	LOGIN(HttpMethod.POST, "/api/login", "ANONYMOUS"),
	SIGNUP(HttpMethod.POST, "/api/signup", "ANONYMOUS"),
	LOGIN_PAGE(HttpMethod.GET, "/login", "ANONYMOUS"),
	SIGNUP_PAGE(HttpMethod.GET, "/signup", "ANONYMOUS"),
	FIND_USER_PAGE(HttpMethod.GET, "/findUser", "ANONYMOUS"),
	CHECK_EMAIL(HttpMethod.POST, "/api/checkEmailDuplicate", "ANONYMOUS"),
	CHECK_NICKNAME(HttpMethod.POST, "/api/checkNicknameDuplicate", "ANONYMOUS"),
	RESET_PASSWORD_REQUEST(HttpMethod.POST, "/api/resetPasswordRequest/{email}", "ANONYMOUS"),
	RESET_PASSWORD_PAGE(HttpMethod.GET, "/resetPassword/{token}", "ANONYMOUS"),
	RESET_PASSWORD(HttpMethod.POST, "/api/resetPassword", "ANONYMOUS"),

	// 노비 권한
	SELF_WITHDRAWAL(HttpMethod.PUT, "/api/selfWithdrawal", "NOBI"),
	LOGOUT(HttpMethod.GET, "/api/logout", "NOBI"),
	FIND_AUTHORITY(HttpMethod.GET, "api/user/findAuthority/{userId}", "NOBI"),
	ADD_AUTHORITY(HttpMethod.POST, "api/user/authority", "NOBI"),
	DELETE_AUTHORITY(HttpMethod.DELETE, "api/user/authority", "NOBI"),

	// 평민 권한
	PYEONGMIN_API(HttpMethod.GET, "/api/pyeongmin/**", "PYEONGMIN"),

	// 양반 권한
	YANGBAN_API(HttpMethod.GET, "/api/yangban/**", "YANGBAN"),

	// 집현전 권한
	JIPHYEONJEON_API(HttpMethod.PUT, "/api/jiphyeonjeon/**", "JIPHYEONJEON"),

	// 세종대왕 권한
	ADMIN_API(HttpMethod.PUT, "/api/admin/**", "KINGSEJONG"),
	ADMIN_PAGE(HttpMethod.GET, "/admin/**", "KINGSEJONG");

	private final HttpMethod method;
	private final String pattern;
	private final String authority;
}
