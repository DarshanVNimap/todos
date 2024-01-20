package com.todoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoApp.dto.EmployeeDto;
import com.todoApp.dto.LoginDto;
import com.todoApp.dto.ResponseDto;
import com.todoApp.dto.TokenResponse;
import com.todoApp.service.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationService authService;
	
	@GetMapping("/home")
	public String home() {
		return "Hiiii";
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDto> register(@RequestBody @Valid EmployeeDto empRequest) throws Exception{
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(empRequest));
	}

	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginDto credential) throws MethodArgumentNotValidException , Exception{
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.login(credential));
	}
	

}
