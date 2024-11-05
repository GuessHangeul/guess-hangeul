package com.estsoft.guesshangeul.userrank.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddUserRankResponse {
	private Long boardManagerApplyId;
	private Long userId;
	private int status;
	private LocalDateTime createdAt;
}
