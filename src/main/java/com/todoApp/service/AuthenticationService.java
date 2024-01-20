package com.todoApp.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import com.todoApp.dto.EmployeeDto;
import com.todoApp.dto.LoginDto;
import com.todoApp.dto.ResponseDto;
import com.todoApp.dto.TokenResponse;

public interface AuthenticationService {
	
	public ResponseDto register(EmployeeDto employee) throws Exception ;
	
	public TokenResponse login(LoginDto loginDto) throws UserPrincipalNotFoundException;

}
