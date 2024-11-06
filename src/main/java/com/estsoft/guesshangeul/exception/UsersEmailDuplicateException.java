package com.estsoft.guesshangeul.exception;

public class UsersEmailDuplicateException extends EntityAttributeDuplicateException {
	public UsersEmailDuplicateException(String value) {
		super("Users", "email", value);
	}
}
