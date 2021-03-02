package com.example.banking.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralException extends ResponseEntityExceptionHandler {
	/*
	 * handles all the validations for @Valid
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder errors = new StringBuilder();
		List<String> errorList = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
				.collect(Collectors.toList());
		for (String error : errorList) {
			errors.append(error + ". ");
		}
		ErrorResponse response = new ErrorResponse(errors.toString());
		return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
	}

	/*
	 * handles ResourceNotFoundException
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		ErrorResponse response = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	/*
	 * handles AccountAlreadyExistsException
	 */
	@ExceptionHandler(AccountAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleAccountAlreadyExistsException(AccountAlreadyExistsException ex) {
		ErrorResponse response = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

	/*
	 * handles InvalidTransactionException, BadRequestException
	 */
	@ExceptionHandler(value = { InvalidTransactionException.class, BadRequestException.class })
	public ResponseEntity<ErrorResponse> handleInvalidTransactionExceptionOrBadRequestException(Exception ex) {
		ErrorResponse response = new ErrorResponse(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	/*
	 * handles all the other exception
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleOtherException(Exception ex) {
		ErrorResponse response = new ErrorResponse("Something went wrong");
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
