package com.estsoft.guesshangeul.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SecurityUrlPattern {
	// 모든 사용자가 접근 가능한 URL 패턴
	// PUBLIC(new String[] {
	// 	"/api/public/"
	// }, null),

	// 로그인 하지 않은 사용자 URL
	ANONYMOUS(new String[] {
		"signup", "api/signup", "login", "api/login", "findUser",
		"api/resetPassword", "api/resetPasswordRequest/"
	}, "ANONYMOUS"),

	// 노비 권한 URL
	NOBI(new String[] {
		"/api/selfWithdrawal", "api/user/findAuthority/"
	}, "NOBI"),

	// 평민 권한 URL
	PYEONGMIN(new String[] {
		"/api/pyeongmin/"
	}, "PYEONGMIN"),

	// 양반 권한 URL
	YANGBAN(new String[] {
		"/api/yangban/"
	}, "YANGBAN"),

	// 집현전 권한 URL
	JIPHYEONJEON(new String[] {
		"/api/jiphyeonjeon/"
	}, "JIPHYEONJEON"),

	// 세종대왕 권한 URL
	KINGSEJONG(new String[] {
		"/api/withDrawal/", "api/user/authority"
	}, "KINGSEJONG");

	private final String[] patterns;
	private final String authority;
}
