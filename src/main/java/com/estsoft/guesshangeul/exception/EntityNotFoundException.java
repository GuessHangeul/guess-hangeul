package com.estsoft.guesshangeul.exception;

public class EntityNotFoundException extends RuntimeException {
	public EntityNotFoundException(String entityName, Long id) {
		super(entityName + " with " + entityName + "Id : " + id + " is not found.");
	}

	public <T> EntityNotFoundException(String entityName, String attributeName, T attributeValue) {
		super(entityName + " with " + attributeName + ": " + attributeValue + " is not found.");
	}
}