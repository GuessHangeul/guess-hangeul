package com.estsoft.guesshangeul.users.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Users {
	private Long id;
	private String email;
	private String password;
	private String nickname;
	private int userrank;
	private int score;
	@JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	private LocalDateTime createdAt;
	@JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	private LocalDateTime connectedAt;
	private int connectCount;
	private String userAgent;
	private boolean isDeleted;

	public Users() {
	}

	public Users(String email, String password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	// 계정의 만료 여부
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정의 잠김 여부
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 계정의 pw정보 만료 여부
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 활성화
	@Override
	public boolean isEnabled() {
		return true;
	}
}
