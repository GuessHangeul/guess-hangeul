package com.estsoft.guesshangeul.user.dto;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.user.entity.Users;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersResponse {
	private Long userId;
	private String nickname;
	private int score;
	private LocalDateTime createdAt;
	private LocalDateTime connectedAt;
	private int connectCount;
	private boolean isDeleted;


	public UsersResponse(Users users) {
		this.userId = users.getId();
		this.nickname = users.getNickname();
		this.score = users.getScore();
		this.createdAt = users.getCreatedAt();
		this.connectedAt = users.getConnectedAt();
		this.connectCount = users.getConnectCount();
		this.isDeleted = users.isDeleted();
	}
}
