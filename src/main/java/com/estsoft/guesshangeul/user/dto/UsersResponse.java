package com.estsoft.guesshangeul.user.dto;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.user.entity.Users;
import com.estsoft.guesshangeul.user.service.UsersDetailsService;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersResponse {
	private Long userId;
	private String nickname;
	private String authority;
	private LocalDateTime createdAt;
	private LocalDateTime connectedAt;
	private int connectCount;
	private boolean isDeleted;


	public UsersResponse(Users users) {
		this.userId = users.getId();
		this.nickname = users.getNickname();
		this.createdAt = users.getCreatedAt();
		this.connectedAt = users.getConnectedAt();
		this.connectCount = users.getConnectCount();
		this.isDeleted = users.isDeleted();
	}
	public UsersResponse(Users users,String authorityString) {
		this.userId = users.getId();
		this.nickname = users.getNickname();
		this.authority = authorityString;
		this.createdAt = users.getCreatedAt();
		this.connectedAt = users.getConnectedAt();
		this.connectCount = users.getConnectCount();
		this.isDeleted = users.isDeleted();
	}
}
