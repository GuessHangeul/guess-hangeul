package com.estsoft.guesshangeul.post.dto;

import static com.estsoft.guesshangeul.util.DateFormatUtil.*;

import com.estsoft.guesshangeul.post.entity.GeneralPost;
import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.user.dto.UsersResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
	private Long id;
	private UsersResponse user;
	private Long boardId;
	private String title;
	private String content;
	private Boolean isHidden;
	private Long view;
	private Long commentCount;
	private String createdAt;
	private String updatedAt;

	public PostResponse(GeneralPost post) {
		this.id = post.getId();
		this.user = new UsersResponse(post.getUsers());
		this.boardId = post.getGeneralBoard().getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.isHidden = post.isHidden();
		this.view = post.getView();
		this.createdAt = post.getCreatedAt().format(formatter);
		this.updatedAt = post.getUpdatedAt().format(formatter);
	}

	public PostResponse(QuizPost post) {
		this.id = post.getId();
		this.user = new UsersResponse(post.getUser());
		this.boardId = post.getId();
		this.title = post.getQuizTitle();
		this.content = post.getHintContent();
		this.isHidden = post.isHidden();
		this.view = post.getView();
		this.createdAt = post.getCreatedAt().format(formatter);
		this.updatedAt = post.getUpdatedAt().format(formatter);
	}
}
