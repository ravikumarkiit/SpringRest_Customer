package com.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice(annotations = RestController.class)
public class CustomerExceptionHandler {
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<String> handleCustomerException(CustomerException ex, WebRequest req) {
		ex.printStackTrace();
		return new ResponseEntity<>(ex.getMessage(), ex.errorCode);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Void> handleRuntimeException(Exception ex, WebRequest req) {
		ex.printStackTrace();
		return new ResponseEntity<Void>(HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
