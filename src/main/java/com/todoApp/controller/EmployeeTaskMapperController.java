package com.todoApp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoApp.dto.TaskAssignDto;
import com.todoApp.service.EmployeeTaskMapperService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/etmapper")
@SecurityRequirement(name = "jwt")
public class EmployeeTaskMapperController {
	
	@Autowired
	private EmployeeTaskMapperService empTaskService;

	@GetMapping
	public ResponseEntity<?> getAllAssignedTask(Principal principal){
		return ResponseEntity.ok(empTaskService.getAllAssignedTask(principal));
	}
	
	@GetMapping("/{taskId}")
	public ResponseEntity<?> getAllEmployeeTaskAssigned(@PathVariable(name = "taskId") Integer taskId , Principal principal){
		return ResponseEntity.status(HttpStatus.OK).body(empTaskService.getAllEmpployeeAssignedTask(taskId, principal));
	}
	
	@PostMapping
	public ResponseEntity<?> assignTask(@RequestBody TaskAssignDto assignDto, Principal principal) throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(empTaskService.assignTask(assignDto, principal));
	}
	
	@DeleteMapping
	public ResponseEntity<?> withdrawTask(@RequestBody TaskAssignDto assignDto , Principal principal) throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(empTaskService.withdrawlTask(assignDto, principal));
	}
	
	@PutMapping("/statusupdate/{taskId}")
	public ResponseEntity<?> updateTaskStatus(@PathVariable(name = "taskId") Integer taskId , Principal principal){
		
		return ResponseEntity.status(HttpStatus.OK).body(empTaskService.updateTaskStatus(taskId, principal));
	}
}
