package com.todoApp.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeResponseDto {
	
	private String name;
	private String email;
	private List<TaskDto> createdTasks;
	private List<EmployeeTaskMapperResponseDto> assignedTo;
	private Date registerAt;
	
}
