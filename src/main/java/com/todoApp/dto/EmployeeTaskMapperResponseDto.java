package com.todoApp.dto;

import java.time.LocalDateTime;

import com.todoApp.entity.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTaskMapperResponseDto {
	
	
	private TaskDto task;
	private TaskStatus status;
	private LocalDateTime assignAt;

}
