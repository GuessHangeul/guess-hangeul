package com.estsoft.guesshangeul.post.dto;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.post.entity.GeneralPost;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeneralPostResponse {
	private Long id;
	private Long userId;
	private Long generalBoardId;
	private String title;
	private String content;
	private boolean isHidden;
	private Long view;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public GeneralPostResponse(GeneralPost generalPost) {
		this.id = generalPost.getId();
		this.userId = generalPost.getUsers().getId();
		this.generalBoardId = generalPost.getGeneralBoard().getId();
		this.title = generalPost.getTitle();
		this.content = generalPost.getContent();
		this.isHidden = generalPost.isHidden();
		this.view = generalPost.getView();
		this.createdAt = generalPost.getCreatedAt();
		this.updatedAt = generalPost.getUpdatedAt();
	}
}