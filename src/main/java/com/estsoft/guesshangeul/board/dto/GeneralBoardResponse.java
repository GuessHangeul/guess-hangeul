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

	public GeneralBoardResponse(GeneralBoard quizBoard) {
		this.id = quizBoard.getId();
		this.title = quizBoard.getTitle();
		this.createdAt = quizBoard.getCreatedAt();
		this.isDeleted = quizBoard.getIsDeleted();
	}
}
