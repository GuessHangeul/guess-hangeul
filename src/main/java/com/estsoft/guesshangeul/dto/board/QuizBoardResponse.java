package com.estsoft.guesshangeul.dto.board;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.entity.QuizBoard;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuizBoardResponse {
	private Long id;
	private String title;
	private Long userId;
	private LocalDateTime createdAt;
	private Boolean isDeleted;

	public QuizBoardResponse(QuizBoard quizBoard) {
		this.id = quizBoard.getId();
		this.title = quizBoard.getTitle();
		this.userId = quizBoard.getUserId();
		this.createdAt = quizBoard.getCreatedAt();
		this.isDeleted = quizBoard.getIsDeleted();
	}
}
