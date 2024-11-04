package com.estsoft.guesshangeul.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuizBoardResponse {
	private Long id;
	private String title;

	public QuizBoardResponse(QuizBoardDto quizBoard) {
		this.id = quizBoard.getId();
		this.title = quizBoard.getTitle();
	}
}
