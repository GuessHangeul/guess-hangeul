package com.estsoft.guesshangeul.exception;

public class UsersNicknameDuplicateException extends EntityAttributeDuplicateException {
	public UsersNicknameDuplicateException(String value) {
		super("Users", "nickname", value);
	}
}
