package com.estsoft.guesshangeul.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CheckEmailExistsRequest {
	@JsonProperty(namespace = "email")
	private String email;
}
