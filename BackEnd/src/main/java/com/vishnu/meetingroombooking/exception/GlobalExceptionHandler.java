package com.vishnu.meetingroombooking.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RoomNotFoundException.class)
	public ResponseEntity<ErrorResponse>handleRoomNotFound(RoomNotFoundException ex){
		
		ErrorResponse error=new ErrorResponse(
				ex.getMessage(),
				HttpStatus.NOT_FOUND.value(),
				LocalDateTime.now()
				);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

		 List<String> errors = ex.getBindingResult()
		            .getFieldErrors()
		            .stream()
		            .map(error -> error.getField() + ": " + error.getDefaultMessage())
		            .toList();

	    ErrorResponse errorResponse = new ErrorResponse(
	            errors.toString(),
	            HttpStatus.BAD_REQUEST.value(),
	            LocalDateTime.now()
	    );

	    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {

	    ErrorResponse error = new ErrorResponse(
	            ex.getMessage(),
	            HttpStatus.BAD_REQUEST.value(),
	            LocalDateTime.now()
	    );

	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
