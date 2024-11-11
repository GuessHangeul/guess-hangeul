package com.estsoft.guesshangeul.userrank.dto;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.user.entity.Users;

import lombok.Getter;

@Getter
public class ViewRankupRequestResponse {
	private Long id;
	private String nickname;
	private String authority;
	private int score;
	private Long userId;
	private int status;
	private LocalDateTime createdAt;
	private int connectcount;

	public ViewRankupRequestResponse(BoardManagerApply apply,String authorityString) {
		this.id = apply.getId();
		this.nickname = apply.getUsers().getNickname();
		this.authority = authorityString;
		this.score = apply.getUsers().getScore();
		this.userId = apply.getUsers().getId();
		this.status = apply.getStatus();
		this.createdAt = apply.getCreatedAt();
		this.connectcount = apply.getUsers().getConnectCount();
	}
}
