package com.estsoft.guesshangeul.user.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guesshangeul.exception.UsersNotFoundException;
import com.estsoft.guesshangeul.user.dto.AddUserRequest;
import com.estsoft.guesshangeul.user.entity.PasswordResetToken;
import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.repository.PasswordResetTokenRepository;
import com.estsoft.guesshangeul.user.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {
	private final UsersRepository usersRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final EmailService emailService;
	private final PasswordResetTokenRepository tokenRepository;

	// 계정 조회
	public Users findUserByEmail(String email) {
		return usersRepository.findByEmail(email).orElseThrow(() -> new UsersNotFoundException("email", email));
	}

	// 회원 가입
	public Users save(AddUserRequest request) {
		request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
		return usersRepository.save(request.toEntity());
	}

	@Value("${app.url}")
	private String appUrl;

	// 비밀번호 변경 메일
	@Transactional
	public void createPasswordResetTokenForUser(Users user) {
		// 기존 토큰이 있다면 삭제
		tokenRepository.deleteByUser(user);

		String token = UUID.randomUUID().toString();
		PasswordResetToken myToken = new PasswordResetToken(user, token);
		tokenRepository.save(myToken);

		String resetUrl = appUrl + "/resetPassword/" + token;
		emailService.sendPasswordResetEmail(user.getEmail(), resetUrl);
	}

	// 토큰 확인
	@Transactional
	public String validatePasswordResetToken(String token) {
		Optional<PasswordResetToken> passwordResetToken = tokenRepository.findByToken(token);

		if (passwordResetToken.isEmpty()) {
			return "invalid";
		}

		if (passwordResetToken.get().isExpired()) {
			tokenRepository.delete(passwordResetToken.get());
			return "expired";
		}

		return "valid";
	}

	// 비밀번호 변경
	@Transactional
	public void changePassword(String token, String newPassword) {
		PasswordResetToken passwordResetToken = tokenRepository.findByToken(token)
			.orElseThrow(() -> new IllegalArgumentException("Invalid token"));

		Users user = passwordResetToken.getUser();
		user.passwordChange(bCryptPasswordEncoder.encode(newPassword));
		tokenRepository.delete(passwordResetToken);
	}
}
