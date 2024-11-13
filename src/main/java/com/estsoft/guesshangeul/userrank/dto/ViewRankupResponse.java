package com.estsoft.guesshangeul.userrank.dto;

import static com.estsoft.guesshangeul.util.DateFormatUtil.*;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.user.dto.RoleType;

import lombok.Getter;

@Getter
public class ViewRankupResponse {
	private Long id;
	private String nickname;
	private String authority;
	private int score;
	private Long userId;
	private int status;
	private String createdAt;
	private int connectcount;

	public ViewRankupResponse(BoardManagerApply apply) {
		this.id = apply.getId();
		this.nickname = apply.getUsers().getNickname();
		this.score = apply.getUsers().getScore();
		this.userId = apply.getUsers().getId();
		this.status = apply.getStatus();
		this.createdAt = apply.getCreatedAt().format(formatter);
		this.connectcount = apply.getUsers().getConnectCount();
	}

	public ViewRankupResponse(BoardManagerApply apply, String authorityString) {
		this.id = apply.getId();
		this.nickname = apply.getUsers().getNickname();
		this.authority = RoleType.toName(authorityString);
		this.score = apply.getUsers().getScore();
		this.userId = apply.getUsers().getId();
		this.status = apply.getStatus();
		this.createdAt = apply.getCreatedAt().format(formatter);
		this.connectcount = apply.getUsers().getConnectCount();
	}
}
