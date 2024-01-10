package com.todoApp.dto;

import java.io.Serializable;
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
public class EmployeeResponseDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5611675304393293857L;
	
	private String name;
	private String email;
	private List<TaskDto> createdTasks;
	private List<EmployeeTaskMapperResponseDto> assignedTo;
	private Date registerAt;
	
	
//	@Override
//	public String toString() {
//		return "EmployeeResponseDto [name=" + name + ", email=" + email + ", createdTasks=" + createdTasks
//				+ ", assignedTo=" + assignedTo + ", registerAt=" + registerAt + "]";
//	}
	
}
