package com.estsoft.guesshangeul.board.dto;

import com.estsoft.guesshangeul.board.entity.QuizBoard;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "문제 게시판 응답")
public class QuizBoardResponse {
	@Schema(description = "게시판 ID", type = "Long")
	private Long id;
	@Schema(description = "게시판 제목", type = "String")
	private String title;
	@Schema(description = "게시판 삭제 여부", type = "Boolean")
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
