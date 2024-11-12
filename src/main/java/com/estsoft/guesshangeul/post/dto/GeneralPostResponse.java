package com.estsoft.guesshangeul.post.dto;

import static com.estsoft.guesshangeul.util.DateFormatUtil.*;

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
	private String nickname;
	private Long generalBoardId;
	private String title;
	private String content;
	private boolean isHidden;
	private Long view;
	private String createdAt;
	private String updatedAt;

	public GeneralPostResponse(GeneralPost generalPost) {
		this.id = generalPost.getId();
		this.userId = generalPost.getUsers().getId();
		this.nickname = generalPost.getUsers().getNickname();
		this.generalBoardId = generalPost.getGeneralBoard().getId();
		this.title = generalPost.getTitle();
		this.content = generalPost.getContent();
		this.isHidden = generalPost.isHidden();
		this.view = generalPost.getView();
		this.createdAt = generalPost.getCreatedAt().format(formatter);
		this.updatedAt = generalPost.getUpdatedAt().format(formatter);
	}
}