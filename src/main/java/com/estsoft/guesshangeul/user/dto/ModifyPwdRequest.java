package com.estsoft.guesshangeul.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ModifyPwdRequest {
	private String token;
	private String password;

	public ModifyPwdRequest(String token) {
		this.token = token;
	}
}
