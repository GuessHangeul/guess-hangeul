package com.estsoft.guesshangeul.userrank.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRankRequest {
	private Long board_manager_apply_id;
	private Long userId;
	private int status;
	private LocalDateTime createdAt;

}
