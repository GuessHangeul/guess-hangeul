package com.estsoft.guesshangeul.admin.dto;

import com.estsoft.guesshangeul.user.entity.Users;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersResponse {
	Long user_id;
	String nickname;

	public UsersResponse(Users users) {
		this.user_id = users.getId();
		this.nickname = users.getNickname();
	}
}
