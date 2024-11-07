package com.estsoft.guesshangeul.exception;

public class InvalidNicknameFormatException extends InvalidInputException {
	public InvalidNicknameFormatException(String nickname) {
		super("nickname", nickname);
	}
}
