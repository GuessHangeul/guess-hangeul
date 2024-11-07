package com.estsoft.guesshangeul.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CheckNicknameExistsRequest {
	private String nickname;
}
