package com.itexperts.bank.service.exception;

public class CardNotFoundException extends RuntimeException{

	public CardNotFoundException(String message) {
		super(message);
	}
}
