package com.estsoft.guesshangeul.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	private final JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String fromEmail;

	public void sendPasswordResetEmail(String toEmail, String resetLink) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setSubject("[ㄴㄹㅁㅆㅁ] 비밀번호 재설정 링크를 확인해 주세요.");
		message.setText("비밀번호를 재설정하려면 다음 링크를 클릭하세요: \n" + resetLink);

		mailSender.send(message);
	}
}
