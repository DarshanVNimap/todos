package com.todoApp.dto;

import java.util.Date;
import java.util.List;

import com.todoApp.entity.EmployeeTaskMapper;
import com.todoApp.entity.Task;

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
	private List<Task> createdTasks;
	private Date registerAt;
	
}
