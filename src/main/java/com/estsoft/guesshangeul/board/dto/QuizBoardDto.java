package com.estsoft.guesshangeul.board.dto;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.board.entity.QuizBoard;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuizBoardDto {
	private Long id;
	private String title;
	private Long userId;
	private LocalDateTime createdAt;
	private Boolean isDeleted;

	public QuizBoardDto(QuizBoard quizBoard) {
		this.id = quizBoard.getId();
		this.title = quizBoard.getTitle();
		this.userId = quizBoard.getUsers().getId();
		this.createdAt = quizBoard.getCreatedAt();
		this.isDeleted = quizBoard.getIsDeleted();
	}
}
