package com.handler;

import org.springframework.http.HttpStatus;

public class CustomerException extends Exception {

	private static final long serialVersionUID = 1L;
	
	HttpStatus errorCode;
	
	public CustomerException(String message) {
		super(message);
	}

	public CustomerException(String message, HttpStatus errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public CustomerException(String message, Throwable throwable, HttpStatus errorCode) {
		super(message, throwable);
		this.errorCode = errorCode;
	}
}
