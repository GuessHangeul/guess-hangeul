package com.estsoft.guesshangeul.board.dto;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GeneralBoardDto {
	private Long id;
	private String title;
	private LocalDateTime createdAt;
	private Boolean isDeleted;

	public GeneralBoardDto(GeneralBoard generalBoard) {
		this.id = generalBoard.getId();
		this.title = generalBoard.getTitle();
		this.createdAt = generalBoard.getCreatedAt();
		this.isDeleted = generalBoard.getIsDeleted();
	}
}
