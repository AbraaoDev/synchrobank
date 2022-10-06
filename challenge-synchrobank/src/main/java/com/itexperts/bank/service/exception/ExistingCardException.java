package com.itexperts.bank.service.exception;

public class ExistingCardException extends RuntimeException{
	
	public ExistingCardException(String message) {
		super(message);
	}
}
