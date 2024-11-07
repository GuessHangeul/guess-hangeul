package com.estsoft.guesshangeul.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyPwdRequest {
	private String token;
	private String password;
}
