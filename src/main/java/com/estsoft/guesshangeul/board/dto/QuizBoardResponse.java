package com.estsoft.guesshangeul.board.dto;

import com.estsoft.guesshangeul.board.entity.QuizBoard;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuizBoardResponse {
	private Long id;
	private String title;
	private Boolean isDeleted;

	public QuizBoardResponse(QuizBoardDto quizBoard) {
		this.id = quizBoard.getId();
		this.title = quizBoard.getTitle();
	}

	public QuizBoardResponse(QuizBoard quizBoard) {
		this.id = quizBoard.getId();
		this.title = quizBoard.getTitle();
		this.isDeleted = quizBoard.getIsDeleted();
	}
}
