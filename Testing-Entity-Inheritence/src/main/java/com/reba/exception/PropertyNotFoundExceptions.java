package com.reba.exception;

public class PropertyNotFoundExceptions extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public PropertyNotFoundExceptions(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
