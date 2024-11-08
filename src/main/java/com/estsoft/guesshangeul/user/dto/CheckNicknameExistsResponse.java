package com.estsoft.guesshangeul.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckNicknameExistsResponse {
	private String nickname;
	private Boolean isExists;
	private String message;
}
