package com.example.banking.exception;

public class ExistingAccountException extends BankSystemException{
	
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Account already exists with Aadhar \"%s\" or PAN \"%s\"";
	
	public ExistingAccountException(String aadhar, String pan) {
		super(String.format(MESSAGE, aadhar, pan));
	}

	public ExistingAccountException(String message) {
		super(message);
	}

}
