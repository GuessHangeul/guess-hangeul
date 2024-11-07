package com.estsoft.guesshangeul.exception;

public class QuizBoardTitleDuplicateException extends EntityAttributeDuplicateException {
	public QuizBoardTitleDuplicateException(String value) {
		super("QuizBoard", "title", value);
	}
}
