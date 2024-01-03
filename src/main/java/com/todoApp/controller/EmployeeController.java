package com.todoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoApp.service.EmployeeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/employee")
@SecurityRequirement(name = "jwt")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;

	@GetMapping()
	@PreAuthorize("hasAuthority('EMPLOYEE::READALL')")
	public ResponseEntity<?> getAllEmployee(){
		return ResponseEntity.ok(empService.getAllEmployee());
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('EMPLOYEE::DELETE')")
	public ResponseEntity<?> removeEmployee(@PathVariable Integer empId){
		return ResponseEntity.ok(empService.removeEmployee(empId));
	}
	
	
	
}
