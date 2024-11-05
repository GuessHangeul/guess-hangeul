package com.estsoft.guesshangeul.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckAnswerRequest {
	private Long quizPostId; // 퀴즈 포스트 ID
	private Long userId; // 유저 ID
	private String answer; // 제출된 답안
}

