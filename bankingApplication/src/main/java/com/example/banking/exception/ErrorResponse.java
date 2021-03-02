package com.example.banking.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
	private LocalDateTime timestamp;
	private String message;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(String message) {
		super();
		this.timestamp = LocalDateTime.now();
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}