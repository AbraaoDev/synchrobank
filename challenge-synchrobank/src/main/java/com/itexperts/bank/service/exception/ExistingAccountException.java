package com.itexperts.bank.service.exception;

public class ExistingAccountException extends RuntimeException{

	public ExistingAccountException(String message) {
		super(message);
	}
}
