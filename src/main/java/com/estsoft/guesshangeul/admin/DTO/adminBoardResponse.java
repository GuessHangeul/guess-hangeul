package com.estsoft.guesshangeul.admin.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class adminBoardResponse {
	private Long id;
	private String email;
	private Long userId;
	private LocalDateTime createdAt;
	private Boolean isDeleted;

	public adminBoardResponse() {
		this.id = getId();
		this.email = getEmail();
		this.createdAt = getCreatedAt();
		this.isDeleted = getIsDeleted();
		this.userId = getUserId();
	}
}
