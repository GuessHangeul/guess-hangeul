package com.estsoft.guesshangeul.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BoardResponse {
	private Long id;
	private String title;
	private LocalDateTime createdAt;
	private Boolean isDeleted;
	private Integer boardType;  // 문제 게시판 or 일반 게시판

	public BoardResponse(GeneralBoardDto generalBoardDto) {
		this.id = generalBoardDto.getId();
		this.title = generalBoardDto.getTitle();
		this.createdAt = generalBoardDto.getCreatedAt();
		this.isDeleted = generalBoardDto.getIsDeleted();
		this.boardType = BoardType.GENERAL_BOARD;
	}

	public BoardResponse(QuizBoardDto quizBoardDto) {
		this.id = quizBoardDto.getId();
		this.title = quizBoardDto.getTitle();
		this.createdAt = quizBoardDto.getCreatedAt();
		this.isDeleted = quizBoardDto.getIsDeleted();
		this.boardType = BoardType.QUIZ_BOARD;
	}
}
