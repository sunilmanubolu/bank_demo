package com.example.banking.exception;

public class BadRequestException extends BankSystemException {

	
	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}
}
