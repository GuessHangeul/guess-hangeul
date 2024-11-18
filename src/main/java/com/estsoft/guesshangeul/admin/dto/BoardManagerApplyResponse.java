package com.estsoft.guesshangeul.admin.dto;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.admin.entity.BoardManagerApply;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "집현전 신청 응답")
public class BoardManagerApplyResponse {
	@Schema(description = "신청 ID", type = "Long")
	private Long id;
	@Schema(description = "유저 ID", type = "Long")
	private Long userId;
	@Schema(description = "신청 상태", type = "int")
	private int status;
	@Schema(description = "신청 날짜", type = "String")
	private LocalDateTime createdAt;
	private String nickname;

	public BoardManagerApplyResponse(BoardManagerApply boardManagerApply) {
		this.id = boardManagerApply.getId();
		this.userId = boardManagerApply.getUsers().getId();
		this.status = boardManagerApply.getStatus();
		this.createdAt = boardManagerApply.getCreatedAt();
	}
	public BoardManagerApplyResponse(BoardManagerApply apply, String nickname) {
		this.id = apply.getId();
		this.userId = apply.getUsers().getId();
		this.status = apply.getStatus();
		this.createdAt = apply.getCreatedAt();
		this.nickname = nickname;
	}
}
