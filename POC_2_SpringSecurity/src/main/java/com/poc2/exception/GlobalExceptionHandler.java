package com.poc2.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
		 
		   @Override
		   	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		   			HttpHeaders headers, HttpStatus status, WebRequest request) {
		   		Map< String, Object> errors = new HashMap<String, Object>();
		   		errors.put("timeStamp", new java.util.Date());
		   		errors.put("status", status.value());
		   		List<String>  error = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
		   		errors.put("error", error);
		   		
		   		return new ResponseEntity<>(errors,headers,status);
		   	}
		   //specific Exception 
		   @ExceptionHandler(ResourceNotFoundException.class)
		   public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
			   ErrorDetails errorDetails = new ErrorDetails(new java.util.Date(), exception.getMessage(), webRequest.getDescription(false));
		       return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
		   }
		   
		   //Global Exception
		   @ExceptionHandler(Exception.class)
		   public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest webRequest) {
			   ErrorDetails errorDetails = new ErrorDetails(new java.util.Date(), exception.getMessage(), webRequest.getDescription(false));
		       return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		   }
}
