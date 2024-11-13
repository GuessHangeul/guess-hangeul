package com.estsoft.guesshangeul.user.dto;

import static com.estsoft.guesshangeul.util.DateFormatUtil.*;

import com.estsoft.guesshangeul.user.entity.Users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "유저 응답")
public class UsersResponse {
	@Schema(description = "유저 ID", type = "Long")
	private Long userId;
	@Schema(description = "유저 닉네임", type = "String")
	private String nickname;
	@Schema(description = "유저 권한", type = "String")
	private String authority;
	@Schema(description = "유저 권한", type = "Integer")
	private Integer score;
	@Schema(description = "유저 생성 날짜", type = "String")
	private String createdAt;
	@Schema(description = "유저 최근 접속 날짜", type = "String")
	private String connectedAt;
	@Schema(description = "유저 접속 횟수", type = "int")
	private int connectCount;
	@Schema(description = "유저 삭제 여부", type = "boolean")
	private boolean isDeleted;

	public UsersResponse(Users users) {
		this.userId = users.getId();
		this.nickname = users.getNickname();
		this.score = users.getScore();
		this.createdAt = users.getCreatedAt().format(formatter);
		this.connectedAt = users.getConnectedAt().format(formatter);
		this.connectCount = users.getConnectCount();
		this.isDeleted = users.isDeleted();
	}

	public UsersResponse(Users users, String authorityString) {
		this.userId = users.getId();
		this.nickname = users.getNickname();
		this.authority = RoleType.toName(authorityString);
		this.score = users.getScore();
		this.createdAt = users.getCreatedAt().format(formatter);
		this.connectedAt = users.getConnectedAt().format(formatter);
		this.connectCount = users.getConnectCount();
		this.isDeleted = users.isDeleted();
	}
}
