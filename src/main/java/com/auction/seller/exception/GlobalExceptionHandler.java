package com.auction.seller.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDeatils> exceptionHandler(Exception ex) {
		ErrorDeatils errorResponse = new ErrorDeatils(ex.getMessage(), new Date());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorDeatils> customException(CustomException exception) {
		ErrorDeatils errorResponse = new ErrorDeatils(exception.getMessage(), new Date());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
