package com.estsoft.guesshangeul.exception;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.UsersRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
	private final UsersRepository usersRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
		Authentication authentication) throws IOException, ServletException {
		AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
	}

	@Override
	@Transactional
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		log.info("call successHandler");

		Users users = (Users)authentication.getPrincipal();
		Users managedUsers = usersRepository.findById(users.getId())
			.orElseThrow(() -> new UsersNotFoundException(users.getId()));

		LocalDateTime connectedAt = managedUsers.getConnectedAt();
		LocalDateTime now = LocalDateTime.now();

		// 첫 방문이거나 다른 날짜에 방문일 경우 카운트 증가
		if (connectedAt == null || !connectedAt.toLocalDate().isEqual(now.toLocalDate())) {
			managedUsers.incrementConnectCount();
		}
		// 접속 시간 업데이트
		managedUsers.updateConnectedAt();

		response.sendRedirect("/");
	}
}
