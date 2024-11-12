package com.estsoft.guesshangeul.board.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.board.entity.QuizBoard;
import com.estsoft.guesshangeul.post.dto.PostResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Schema(description = "게시판 별 게시글 목록 조회")
public class BoardResponse {
	@Schema(description = "게시판 ID", type = "Long")
	private Long id;
	@Schema(description = "게시판 제목", type = "String")
	private String title;
	@Schema(description = "게시판 생성 날짜", type = "LocalDateTime")
	private LocalDateTime createdAt;
	@Schema(description = "게시판 삭제 여부", type = "Boolean")
	private Boolean isDeleted;
	@Schema(description = "일반 게시판(1) or 문제 게시판(2)", type = "Integer")
	private Integer boardType;  // 일반 게시판(1) or 문제 게시판(2)
	@Setter
	private List<PostResponse> posts;

	public BoardResponse(GeneralBoard generalBoard) {
		this.id = generalBoard.getId();
		this.title = generalBoard.getTitle();
		this.createdAt = generalBoard.getCreatedAt();
		this.isDeleted = generalBoard.getIsDeleted();
		this.boardType = BoardType.GENERAL_BOARD;
	}

	public BoardResponse(QuizBoard quizBoard) {
		this.id = quizBoard.getId();
		this.title = quizBoard.getTitle();
		this.createdAt = quizBoard.getCreatedAt();
		this.isDeleted = quizBoard.getIsDeleted();
		this.boardType = BoardType.QUIZ_BOARD;
	}
}
