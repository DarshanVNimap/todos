package com.todoApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FilterExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ProblemDetail exception(Exception e) {
		ProblemDetail error = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_GATEWAY, e.getMessage());
		return error;
	}

}
