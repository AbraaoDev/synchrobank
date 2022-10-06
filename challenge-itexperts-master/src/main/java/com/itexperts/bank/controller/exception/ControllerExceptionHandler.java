package com.itexperts.bank.controller.exception;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.itexperts.bank.model.exception.StandardError;
import com.itexperts.bank.model.exception.ValidationError;
import com.itexperts.bank.service.exception.AccountActiveCardsException;
import com.itexperts.bank.service.exception.AccountNotFoundException;
import com.itexperts.bank.service.exception.CardNotFoundException;
import com.itexperts.bank.service.exception.ExistingAccountException;
import com.itexperts.bank.service.exception.ExistingCardException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<StandardError> accountNotFound(AccountNotFoundException exception, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setCode(HttpStatus.NOT_FOUND.value());
		err.setStatus(HttpStatus.NOT_FOUND.name());
		err.setMessage(exception.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(CardNotFoundException.class)
	public ResponseEntity<StandardError> cardNotFound(CardNotFoundException exception, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setCode(HttpStatus.NOT_FOUND.value());
		err.setStatus(HttpStatus.NOT_FOUND.name());
		err.setMessage(exception.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(ExistingAccountException.class)
	public ResponseEntity<StandardError> existingAccount(ExistingAccountException exception, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setCode(HttpStatus.BAD_REQUEST.value());
		err.setStatus(HttpStatus.BAD_REQUEST.name());
		err.setMessage(exception.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(ExistingCardException.class)
	public ResponseEntity<StandardError> existingCard(ExistingCardException exception, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setCode(HttpStatus.BAD_REQUEST.value());
		err.setStatus(HttpStatus.BAD_REQUEST.name());
		err.setMessage(exception.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(AccountActiveCardsException.class)
	public ResponseEntity<StandardError> accountActiveCards(AccountActiveCardsException exception, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setCode(HttpStatus.BAD_REQUEST.value());
		err.setStatus(HttpStatus.BAD_REQUEST.name());
		err.setMessage(exception.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request){
		List<ValidationError> errors = exception.getBindingResult().getFieldErrors().stream()
        .map(error -> new ValidationError(error.getObjectName(), error.getField(), error.getDefaultMessage()))
        .collect(Collectors.toList());
		
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setCode(HttpStatus.BAD_REQUEST.value());
		err.setStatus(HttpStatus.BAD_REQUEST.name());
		err.setMessage("Um ou mais dados não estão no formato esperado.");
		err.setPath(request.getRequestURI());
		err.setErrors(errors);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<StandardError> invalidFormat(InvalidFormatException exception, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setCode(HttpStatus.BAD_REQUEST.value());
		err.setStatus(HttpStatus.BAD_REQUEST.name());
		err.setMessage("Valores válidos para 'flag' [ELO, VISA, MASTERCARD].");
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
