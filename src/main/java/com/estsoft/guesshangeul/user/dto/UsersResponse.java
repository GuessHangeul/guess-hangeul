package com.estsoft.guesshangeul.user.dto;

import com.estsoft.guesshangeul.user.entity.Users;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersResponse {
	Long userId;
	String nickname;

	public UsersResponse(Users users) {
		this.userId = users.getId();
		this.nickname = users.getNickname();
	}
}
