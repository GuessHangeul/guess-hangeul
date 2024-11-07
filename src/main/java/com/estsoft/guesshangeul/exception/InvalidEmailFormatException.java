package com.estsoft.guesshangeul.exception;

public class InvalidEmailFormatException extends InvalidInputException {
	public InvalidEmailFormatException(String email) {
		super("email", email);
	}
}
