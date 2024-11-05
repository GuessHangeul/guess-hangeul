package com.estsoft.guesshangeul.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GeneralBoardResponse {
	private Long id;
	private String title;
	private Long userId;
	private LocalDateTime createdAt;
	private Boolean isDeleted;

	public GeneralBoardResponse(GeneralBoardDto generalBoard) {
		this.id = generalBoard.getId();
		this.title = generalBoard.getTitle();
		this.createdAt = generalBoard.getCreatedAt();
		this.isDeleted = generalBoard.getIsDeleted();
	}
}
