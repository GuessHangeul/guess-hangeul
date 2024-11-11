package com.estsoft.guesshangeul.post.dto;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.user.dto.UsersResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralPostWithCommentCountResponse {
	private Long id;
	private UsersResponse user;
	private Long generalBoardId;
	private String title;
	private String content;
	private Boolean isHidden;
	private Long view;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Long commentCount;

	public GeneralPostWithCommentCountResponse(GeneralPostWithCommentCountInterface dto) {
		this.id = dto.getId();
		this.user = new UsersResponse(dto.getUsers());
		this.generalBoardId = dto.getGeneralBoard().getId();
		this.title = dto.getTitle();
		this.content = dto.getContent();
		this.isHidden = dto.getIsHidden();
		this.view = dto.getView();
		this.createdAt = dto.getCreatedAt();
		this.updatedAt = dto.getUpdatedAt();
		this.commentCount = dto.getCommentCount();
	}
}