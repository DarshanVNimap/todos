package com.todoApp.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoApp.entity.Employee;
import com.todoApp.repository.EmployeeRepository;

@Service
public class AppService {

	@Autowired
	private EmployeeRepository empRepo;
	
	public Employee getEmployee(Principal principal) {
		String email = principal.getName();
		return empRepo.findByEmail(email).get();

	}
}
