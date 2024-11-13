package com.estsoft.guesshangeul.post.dto;

import com.estsoft.guesshangeul.post.entity.GeneralPost;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddGeneralPostRequest {
	private String title;
	private String content;
	private boolean isHidden;
	private Long view;

	public GeneralPost toEntity() {
		GeneralPost generalPost = new GeneralPost();
		generalPost.setTitle(this.title);
		generalPost.setContent(this.content);
		generalPost.setHidden(this.isHidden);
		generalPost.setView(this.view);
		return generalPost;
	}
}