package com.reba.exception;

public class DealNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public DealNotFoundException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
