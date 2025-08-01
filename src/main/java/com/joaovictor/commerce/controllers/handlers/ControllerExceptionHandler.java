package com.joaovictor.commerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.joaovictor.commerce.dto.CustomError;
import com.joaovictor.commerce.dto.ValidationError;
import com.joaovictor.commerce.services.exceptions.DatabaseException;
import com.joaovictor.commerce.services.exceptions.ForbiddenException;
import com.joaovictor.commerce.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomError> resourceNotFound(
			ResourceNotFoundException e,
			HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomError err = new CustomError(
				Instant.now(), 
				status.value(), 
				e.getMessage(), 
				request.getRequestURI()
				);
		return ResponseEntity.status(status).body(err);		
		
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<CustomError> database(
			DatabaseException e,
			HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		CustomError err = new CustomError(
				Instant.now(), 
				status.value(), 
				e.getMessage(), 
				request.getRequestURI()
				);
		return ResponseEntity.status(status).body(err);		
		
	}
	
	//Validação de campos
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> methodArgumentNotValid(
			MethodArgumentNotValidException e,
			HttpServletRequest request){
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError err = new ValidationError(
				Instant.now(), 
				status.value(), 
				"Dados Invalidos", 
				request.getRequestURI()
				);
		
		//Para pegar os erros na classe MethodArgumentNotValidException
		for(FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(err);		
		
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<CustomError> forbidden(
			ForbiddenException e,
			HttpServletRequest request){
		HttpStatus status = HttpStatus.FORBIDDEN;
		CustomError err = new CustomError(
				Instant.now(), 
				status.value(), 
				e.getMessage(), 
				request.getRequestURI()
				);
		return ResponseEntity.status(status).body(err);		
		
	}
}
