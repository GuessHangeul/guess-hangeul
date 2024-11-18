package com.estsoft.guesshangeul.user.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteUsersRequest {
	private Long userId;
	private boolean isDeleted;
}
