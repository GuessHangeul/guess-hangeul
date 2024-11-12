package com.estsoft.guesshangeul.board.dto;

import static com.estsoft.guesshangeul.util.DateFormatUtil.*;

import java.util.List;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.board.entity.QuizBoard;
import com.estsoft.guesshangeul.post.dto.PostResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class BoardResponse {
	private Long id;
	private String title;
	private String createdAt;
	private Boolean isDeleted;
	private Integer boardType;  // 일반 게시판(1) or 문제 게시판(2)
	@Setter
	private List<PostResponse> posts;

	public BoardResponse(GeneralBoard generalBoard) {
		this.id = generalBoard.getId();
		this.title = generalBoard.getTitle();
		this.createdAt = generalBoard.getCreatedAt().format(formatter);
		this.isDeleted = generalBoard.getIsDeleted();
		this.boardType = BoardType.GENERAL_BOARD;
	}

	public BoardResponse(QuizBoard quizBoard) {
		this.id = quizBoard.getId();
		this.title = quizBoard.getTitle();
		this.createdAt = quizBoard.getCreatedAt().format(formatter);
		this.isDeleted = quizBoard.getIsDeleted();
		this.boardType = BoardType.QUIZ_BOARD;
	}
}
