package com.estsoft.guesshangeul.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckEmailExistsResponse {
	private String email;
	private Boolean isExists;
	private String message;
}
