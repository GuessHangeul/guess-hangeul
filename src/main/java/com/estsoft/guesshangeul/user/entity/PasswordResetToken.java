package com.estsoft.guesshangeul.user.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PasswordResetToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "token")
	private String token;

	@OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private Users user;

	@Column(name = "expiry_date")
	private LocalDateTime expiryDate;

	public PasswordResetToken(Users user, String token) {
		this.user = user;
		this.token = token;
		this.expiryDate = LocalDateTime.now().plusHours(24); // 24시간 유효
	}

	public boolean isExpired() {
		return LocalDateTime.now().isAfter(this.expiryDate);
	}
}