package com.estsoft.guesshangeul.user.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthorityResponse {
	private Long id;
	private Long userId;
	private String authority;

	public AuthorityResponse(String authority) {
		this.authority = authority;
	}
}
