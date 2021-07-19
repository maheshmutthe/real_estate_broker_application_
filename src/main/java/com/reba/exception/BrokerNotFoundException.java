package com.reba.exception;

public class BrokerNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String message;
	
	public BrokerNotFoundException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
