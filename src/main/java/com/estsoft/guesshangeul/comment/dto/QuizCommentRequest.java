package com.estsoft.guesshangeul.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizCommentRequest {
	private String content;
	private Long userId;
	private Long postId;
}