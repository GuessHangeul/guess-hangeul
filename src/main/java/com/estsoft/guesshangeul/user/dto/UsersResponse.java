package com.estsoft.guesshangeul.user.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersResponse {
	private Long user_id;
	private String email;
	private String nickname;
	private int user_rank;
	private int score;
	private LocalDateTime createdAt;
	private LocalDateTime connrectedAt;
	private int connectCount;
	private String userAgent;
	private boolean isDeleted;

	public UsersResponse(Users users) {
		this.user_id = users.getId();
		this.email = users.getEmail();
		this.nickname = users.getNickname();
		this.user_rank = users.getUserrank();
		this.score = users.getScore();
		this.createdAt = users.getCreatedAt();
		this.connrectedAt = users.getCreatedAt();
		this.connectCount = users.getConnectCount();
		this.userAgent = users.getUserAgent();
		this.isDeleted = users.isDeleted();
	}

}
