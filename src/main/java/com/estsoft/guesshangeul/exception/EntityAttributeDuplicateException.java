package com.estsoft.guesshangeul.exception;

public class EntityAttributeDuplicateException extends RuntimeException {
	public <T> EntityAttributeDuplicateException(String entityName, String attributeName, T attributeValue) {
		super(entityName + " is duplicated on " + attributeName + " : " + attributeValue + ".");
	}
}
