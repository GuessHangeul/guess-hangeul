package com.estsoft.guesshangeul.user.dto;

import com.estsoft.guesshangeul.user.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AddUserRequest {
	private String email;
	private String password;
	private String nickname;

	public Users toEntity() {
		return new Users(email, password, nickname);
	}
}