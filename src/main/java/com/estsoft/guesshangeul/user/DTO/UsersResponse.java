package com.estsoft.guesshangeul.user.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UsersResponse {
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

	public UsersResponse(Users users) {
		Users usersFormDisplay = users.getUsers();

		id = users.getId();
		email = users.getEmail();
		password = users.getPassword();
		nickname = users.getNickname();
		userrank = users.getUserrank();
		score = users.getScore();
		createdAt = users.getCreatedAt();
		connectedAt = users.getConnectedAt();
		connectCount = users.getConnectCount();
		userAgent = users.getUserAgent();
		isDeleted = users.isDeleted();
	}
}
