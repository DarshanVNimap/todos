package com.todoApp.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDto {
	
	private String title;
	
	private String description;
	
	private LocalDate startAt;
	
	private LocalDate endAt;
	
//	private List<> assignedTo;
	

}
