package com.estsoft.guesshangeul.userrank.dto;

import static com.estsoft.guesshangeul.util.DateFormatUtil.*;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;
import com.estsoft.guesshangeul.user.dto.RoleType;

import lombok.Getter;

@Getter
public class ViewRankupRequestResponse {
	private Long id;
	private String nickname;
	private String authority;
	private int score;
	private Long userId;
	private int status;
	private String createdAt;
	private int connectcount;

	public ViewRankupRequestResponse(BoardManagerApply apply, String authorityString) {
		this.id = apply.getId();
		this.nickname = apply.getUsers().getNickname();
		this.authority = RoleType.toName(authorityString);
		this.score = apply.getUsers().getScore();
		this.userId = apply.getUsers().getId();
		this.status = apply.getStatus();
		this.createdAt = apply.getCreatedAt().format(formatter);
		this.connectcount = apply.getUsers().getConnectCount();
	}

	public ViewRankupRequestResponse(BoardManagerApply boardManagerApply) {
		this.id = boardManagerApply.getId();
		this.userId = boardManagerApply.getUsers().getId(); // Users 엔티티의 ID 값
		this.nickname = boardManagerApply.getUsers().getNickname(); // Users 엔티티의 닉네임 값
		this.status = boardManagerApply.getStatus();
		this.createdAt = boardManagerApply.getCreatedAt().format(formatter);
	}
}
