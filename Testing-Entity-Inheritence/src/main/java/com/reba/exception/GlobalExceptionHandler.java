package com.reba.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import oracle.jdbc.OracleDatabaseException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(OracleDatabaseException.class)
	public ResponseEntity<Object> handleDataBaseException(OracleDatabaseException exception, WebRequest webRequest){
		logger.error(exception.getMessage());
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(exception.getMessage());
		ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.CONFLICT);
		return entity;
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserExceptions(UserNotFoundException exception, WebRequest webRequest){
		logger.error(exception.getMessage());
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(exception.getMessage());
		ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		return entity;
	}
	
	@ExceptionHandler(UserIncorrectPasswordException.class)
	public ResponseEntity<Object> handlePasswordException(UserIncorrectPasswordException exception, WebRequest webRequest){
		logger.error(exception.getMessage());
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(exception.getMessage());
		ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
		return entity;
	}
	
	@ExceptionHandler(PropertyNotFoundExceptions.class)
	public ResponseEntity<Object> handlePropertyException(PropertyNotFoundExceptions exception, WebRequest webRequest){
		logger.error(exception.getMessage());
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(exception.getMessage());
		ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		return entity;
	}
	
	@ExceptionHandler(BrokerNotFoundException.class)
	public ResponseEntity<Object> handleBrokerException(BrokerNotFoundException exception, WebRequest webRequest){
		logger.error(exception.getMessage());
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(exception.getMessage());
		ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		return entity;
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<Object> handleCustomerException(CustomerNotFoundException exception, WebRequest webRequest){
		logger.error(exception.getMessage());
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(exception.getMessage());
		ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		return entity;
	}
	
	@ExceptionHandler(DealNotFoundException.class)
	public ResponseEntity<Object> handleDealException(DealNotFoundException exception, WebRequest webRequest){
		logger.error(exception.getMessage());
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(exception.getMessage());
		ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		return entity;
	}
	
	@ExceptionHandler(DealCouldNotBeMadeException.class)
	public ResponseEntity<Object> handleDealNotMadeException(DealCouldNotBeMadeException exception, WebRequest webRequest){
		logger.error(exception.getMessage());
		ExceptionResponse response = new ExceptionResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(exception.getMessage());
		ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		return entity;
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest){
		logger.error(ex.getMessage());
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
           
        });
        ResponseEntity<Object> entity = new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        return entity;
	}
}
