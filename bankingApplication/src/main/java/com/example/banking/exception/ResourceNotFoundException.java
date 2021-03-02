package com.example.banking.exception;

public class ResourceNotFoundException extends BankSystemException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
