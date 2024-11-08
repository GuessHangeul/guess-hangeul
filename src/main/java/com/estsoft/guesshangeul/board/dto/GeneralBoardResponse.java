package com.estsoft.guesshangeul.board.dto;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;

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

	public GeneralBoardResponse(GeneralBoard generalBoard) {
		this.id = generalBoard.getId();
		this.title = generalBoard.getTitle();
		this.createdAt = generalBoard.getCreatedAt();
		this.isDeleted = generalBoard.getIsDeleted();
	}

	public GeneralBoardResponse(GeneralBoardDto generalBoard) {
		this.id = generalBoard.getId();
		this.title = generalBoard.getTitle();
		this.createdAt = generalBoard.getCreatedAt();
		this.isDeleted = generalBoard.getIsDeleted();
	}
}
