package com.todoApp.dto;

import java.time.LocalDateTime;

import com.todoApp.entity.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskAssignResponseDto {
	
	private EmployeeTaskDto employee;
	private TaskStatus status;
	private LocalDateTime assignAt;

}
