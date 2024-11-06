package com.estsoft.guesshangeul.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckAnswerRequest {
	private Long quizPostId;
	private Long userId;
	private String answer;
}

