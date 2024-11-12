package com.estsoft.guesshangeul.board.dto;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Schema(description = "일반 게시판 응답")
public class GeneralBoardResponse {
	@Schema(description = "게시판 ID", type = "Long")
	private Long id;
	@Schema(description = "게시판 제목", type = "String")
	private String title;
	@Schema(description = "게시판 생성 날짜", type = "LocalDateTime")
	private LocalDateTime createdAt;
	@Schema(description = "게시판 삭제 여부", type = "Boolean")
	private Boolean isDeleted;

	public GeneralBoardResponse(GeneralBoard generalBoard) {
		this.id = generalBoard.getId();
		this.title = generalBoard.getTitle();
		this.createdAt = generalBoard.getCreatedAt();
		this.isDeleted = generalBoard.getIsDeleted();
	}

	public GeneralBoardResponse(GeneralBoardDto generalBoard) {
		this.id = generalBoard.getId();
		this.title = generalBoard.getTitle();
		this.createdAt = generalBoard.getCreatedAt();
		this.isDeleted = generalBoard.getIsDeleted();
	}
}
