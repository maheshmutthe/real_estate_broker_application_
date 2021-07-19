package com.reba.exception;

public class UserIncorrectPasswordException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String message;

	public UserIncorrectPasswordException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
