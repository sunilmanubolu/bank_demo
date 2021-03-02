package com.example.banking.exception;

public class InvalidTransactionException extends BankSystemException {
	private static final long serialVersionUID = 1L;

	public InvalidTransactionException(String message) {
		super(message);
	}
}
