package com.reba.exception;

public class DealCouldNotBeMadeException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String message;
	
	public DealCouldNotBeMadeException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
