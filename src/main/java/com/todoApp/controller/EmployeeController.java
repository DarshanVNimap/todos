package com.todoApp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
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
//	@Cacheable(value = "List<EmployeeResponseDto>" , key = "#principal.name")
	public ResponseEntity<?> getAllEmployee(Principal principal){
		return ResponseEntity.ok(empService.getAllEmployee(principal));
	}
	
	@GetMapping("/{empId}")
	@PreAuthorize("hasAuthority('EMPLOYEE::READBYID')")
	public ResponseEntity<?> getEmployeeById(@PathVariable(name = "empId") Integer empId){
		return ResponseEntity.status(HttpStatus.OK).body(empService.getEmployeeById(empId));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('EMPLOYEE::DELETE')")
	@CacheEvict(value = "todos")
	public ResponseEntity<?> removeEmployee(@PathVariable Integer empId){
		return ResponseEntity.ok(empService.removeEmployee(empId));
	}
	
	
	
	
	
	
	
}
