package com.itexperts.bank.service.exception;

public class AccountNotFoundException extends RuntimeException{

	public AccountNotFoundException(String message) {
		super(message);
	}
}
