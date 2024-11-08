package com.estsoft.guesshangeul.admin.dto;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardManagerApplyResponse {
	private Long id;
	private Long userId;
	private int status;
	private LocalDateTime createdAt;

	public BoardManagerApplyResponse(BoardManagerApply boardManagerApply) {
		this.id = boardManagerApply.getId();
		this.userId = boardManagerApply.getUsers().getId();
		this.status = boardManagerApply.getStatus();
		this.createdAt = boardManagerApply.getCreatedAt();
	}
}
