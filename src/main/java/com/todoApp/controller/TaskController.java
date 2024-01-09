package com.todoApp.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

import com.todoApp.dto.ResponseDto;
import com.todoApp.dto.TaskDto;
import com.todoApp.service.TaskService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/task")
@SecurityRequirement(name = "jwt")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/created")
	@Cacheable(value = "TaskResponseDto")
	public ResponseEntity<?> getAllCreatedTasks(Principal principal) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllCreatedTasks(principal));
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
								 ResponseDto.builder()
								 				.message(e.getMessage())
								 				.status(HttpStatus.BAD_REQUEST)
								 				.time(new Date())
								 				.build()
								 				);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTaskById(@PathVariable Integer id , Principal principal) throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(principal, id));
	}
	
	
	@PostMapping
	public ResponseEntity<?> addTask(@RequestBody @Valid TaskDto taskdto , Principal principal) {
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(taskService.addTask(taskdto, principal));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					 ResponseDto.builder()
					 				.message(e.getMessage())
					 				.status(HttpStatus.BAD_REQUEST)
					 				.time(new Date())
					 				.build()
					 				);
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateTask(@RequestBody TaskDto taskdto, @PathVariable(name = "id") Integer id , Principal principal) {
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(taskService.updateTask(id, taskdto, principal));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					 ResponseDto.builder()
					 				.message(e.getMessage())
					 				.status(HttpStatus.BAD_REQUEST)
					 				.time(new Date())
					 				.build()
					 				);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeTask(@PathVariable(name = "id") Integer id) {
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(taskService.deleteTask(id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					 ResponseDto.builder()
					 				.message(e.getMessage())
					 				.status(HttpStatus.BAD_REQUEST)
					 				.time(new Date())
					 				.build()
					 				);
		}
	}

}
