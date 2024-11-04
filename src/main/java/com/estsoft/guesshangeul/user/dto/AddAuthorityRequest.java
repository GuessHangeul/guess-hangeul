package com.estsoft.guesshangeul.user.dto;

import com.estsoft.guesshangeul.user.entity.Authorities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AddAuthorityRequest {
	private Long userId;
	private String authority;

	public Authorities toEntity() {
		return new Authorities(userId, authority);
	}
}
