package com.reba.exception;

public class CustomerNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String message;
	
	public CustomerNotFoundException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
