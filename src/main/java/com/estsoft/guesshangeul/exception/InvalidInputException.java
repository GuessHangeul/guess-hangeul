package com.estsoft.guesshangeul.exception;

public class InvalidInputException extends RuntimeException {
	public <T> InvalidInputException(String inputName, T inputValue) {
		super("Wrong " + inputName + ": " + inputValue);
	}
}
