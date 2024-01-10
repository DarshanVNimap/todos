package com.todoApp.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("TaskResponseDto")
public class TaskResponseDto {
	
	private String title;
	
	private String description;
	
	private LocalDate startAt;
	
	private LocalDate endAt;
	
	private List<TaskAssignResponseDto> assignTo;
	

}
