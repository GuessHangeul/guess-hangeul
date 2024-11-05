package com.estsoft.guesshangeul.exception;

public class UsersNotFoundException extends EntityNotFoundException {
	public UsersNotFoundException(Long userId) {
		super("users", userId);
	}

	public <T> UsersNotFoundException(String attributeName, T attributeValue) {
		super("Users", attributeName, attributeValue);
	}
}
