package com.estsoft.guesshangeul.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckAnswerResponse {
	private boolean correct;
	private int score;
	private String message;
}