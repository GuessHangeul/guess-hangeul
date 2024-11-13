package com.estsoft.guesshangeul.comment.dto;

import static com.estsoft.guesshangeul.util.DateFormatUtil.*;

import com.estsoft.guesshangeul.comment.entity.GeneralComment;
import com.estsoft.guesshangeul.comment.entity.QuizComment;
import com.estsoft.guesshangeul.user.dto.UsersResponse;

import lombok.Getter;

@Getter
public class CommentResponse {
	private Long id;
	private UsersResponse users;
	private Long postId;
	private String content;
	private String createdAt;

	public CommentResponse(GeneralComment comment) {
		this.id = comment.getId();
		this.users = new UsersResponse(comment.getUsers());
		this.postId = comment.getPost().getId();
		this.content = comment.getContent();
		this.createdAt = comment.getCreatedAt().format(formatter);
	}

	public CommentResponse(QuizComment comment) {
		this.id = comment.getId();
		this.users = new UsersResponse(comment.getUser());
		this.postId = comment.getPost().getId();
		this.content = comment.getContent();
		this.createdAt = comment.getCreatedAt().format(formatter);
	}
}
