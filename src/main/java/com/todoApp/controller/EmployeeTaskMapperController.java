package com.todoApp.controller;

import java.security.Principal;
import java.time.LocalDate;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todoApp.dto.TaskAssignDto;
import com.todoApp.entity.TaskStatus;
import com.todoApp.service.EmployeeTaskMapperService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/etmapper")
@SecurityRequirement(name = "jwt")
public class EmployeeTaskMapperController {
	
	@Autowired
	private EmployeeTaskMapperService empTaskService;

	@GetMapping("/assigned")
	public ResponseEntity<?> getAllAssignedTask(Principal principal){
		return ResponseEntity.ok(empTaskService.getAllAssignedTask(principal));
	}
	
	@GetMapping("/{taskId}")
	public ResponseEntity<?> getAllEmployeeTaskAssigned(@PathVariable(name = "taskId") Integer taskId , Principal principal){
		return ResponseEntity.status(HttpStatus.OK).body(empTaskService.getAllEmpployeeAssignedTask(taskId, principal));
	}
	
	@GetMapping("/filter")
	public ResponseEntity<?> filterAssignTask(@RequestParam(name = "status"  ,required = false) TaskStatus status ,@RequestParam(name = "startDate" ,required = false) LocalDate start_date ,@RequestParam(name = "endDate" , required = false) LocalDate end_date , Principal principal){
		return ResponseEntity.status(HttpStatus.OK).body(empTaskService.filterAssignTask(status, start_date, end_date, principal));
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
	public ResponseEntity<?> updateTaskStatus(@PathVariable(name = "taskId") Integer taskId , Principal principal) throws Exception{
		
		return ResponseEntity.status(HttpStatus.OK).body(empTaskService.updateTaskStatus(taskId, principal));
	}
	
	
}
