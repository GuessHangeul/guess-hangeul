package com.estsoft.guesshangeul.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckAnswerResponse {
	private boolean correct; // 정답 여부
	private int score; // 현재 점수
	private String message; // 메시지
}