package com.todoApp.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.todoApp.dto.ResponseDto;

@RestControllerAdvice
public class FilterExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		Map<String , String> errors = new HashMap<>();
		
		BindingResult bindingResult = ex.getBindingResult();
		
		  for (FieldError fieldError : bindingResult.getFieldErrors()) {
			  errors.put(fieldError.getField(), fieldError.getDefaultMessage());
	        }

		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
	
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ResponseEntity<?> exception(Exception e) {
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							 .body(ResponseDto.builder()
									 		  .message(e.getMessage())
									 		  .status(HttpStatus.BAD_REQUEST)
									 		  .time(new Date())
									 		  .build());
	}

}
