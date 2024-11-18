package com.estsoft.guesshangeul.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralCommentRequest {
	private String content;
	private Long userId;
	private Long postId;
}