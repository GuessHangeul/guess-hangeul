package com.estsoft.guesshangeul.user.dto;

import static com.estsoft.guesshangeul.util.DateFormatUtil.*;

import com.estsoft.guesshangeul.user.entity.Users;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersResponse {
	private Long userId;
	private String nickname;
	private String authority;
	private String createdAt;
	private String connectedAt;
	private int connectCount;
	private boolean isDeleted;

	public UsersResponse(Users users) {
		this.userId = users.getId();
		this.nickname = users.getNickname();
		this.createdAt = users.getCreatedAt().format(formatter);
		this.connectedAt = users.getConnectedAt().format(formatter);
		this.connectCount = users.getConnectCount();
		this.isDeleted = users.isDeleted();
	}

	public UsersResponse(Users users, String authorityString) {
		this.userId = users.getId();
		this.nickname = users.getNickname();
		this.authority = authorityString;
		this.createdAt = users.getCreatedAt().format(formatter);
		this.connectedAt = users.getConnectedAt().format(formatter);
		this.connectCount = users.getConnectCount();
		this.isDeleted = users.isDeleted();
	}
}
