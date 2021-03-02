package com.example.banking.exception;

public class BankSystemException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public BankSystemException(String message) {
		super(message);
	}

	public BankSystemException() {
		super();
	}
}
