package com.estsoft.guesshangeul.user.controller;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException exception) throws IOException {
		log.info("call failureHandler");

		// 실패로직 핸들링
		String message = getExceptionMessage(exception);

		message = URLEncoder.encode(message, "UTF-8");
		response.sendRedirect("/login?message=" + message);

	}

	private String getExceptionMessage(AuthenticationException exception) {
		if (exception instanceof BadCredentialsException) {
			return "아이디 또는 비밀번호가 일치하지 않습니다.";
		} else if (exception instanceof UsernameNotFoundException) {
			return "계정이 존재하지 않습니다.";
		} else if (exception instanceof AccountExpiredException) {
			return "계정이 만료되었습니다.";
		} else if (exception instanceof CredentialsExpiredException) {
			return "비밀번호가 만료되었습니다.";
		} else if (exception instanceof DisabledException) {
			return "계정이 비활성화되었습니다.";
		} else if (exception instanceof LockedException) {
			return "계정이 잠금처리되었습니다.";
		} else {
			return "알 수 없는 오류 입니다.";
		}
	}
}
