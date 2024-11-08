package com.estsoft.guesshangeul.userrank.dto;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;

import lombok.Getter;

@Getter
public class ViewRankupRequestResponse {
	private Long id;
	private Long userId;
	private int status;
	private LocalDateTime createdAt;

	public ViewRankupRequestResponse(BoardManagerApply apply) {
		this.id = apply.getId();
		this.userId = apply.getUsers().getId();
		this.status = apply.getStatus();
		this.createdAt = apply.getCreatedAt();
	}
}
