package com.todoApp.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import com.todoApp.dto.EmployeeTaskMapperResponseDto;
import com.todoApp.dto.IFilterTaskDto;
import com.todoApp.dto.ResponseDto;
import com.todoApp.dto.TaskAssignDto;
import com.todoApp.entity.TaskStatus;

public interface EmployeeTaskMapperService {
	
	public List<EmployeeTaskMapperResponseDto> getAllAssignedTask(Principal principal);
	
	public List<EmployeeTaskMapperResponseDto> getAllEmpployeeAssignedTask(Integer taskId , Principal principal);
	
	public List<IFilterTaskDto> filterAssignTask(TaskStatus status ,LocalDate start_date , LocalDate end_date , Principal principal);
	
	public ResponseDto assignTask(TaskAssignDto assignDto ,Principal principal) throws Exception;
	
	public ResponseDto withdrawlTask(TaskAssignDto assignDto, Principal principal) throws Exception ;
	
	public ResponseDto updateTaskStatus(Integer taskId ,Principal principal) throws Exception;
	
	
	
//	public ResponseDto update(List<Integer> empId , Integer taskId ,Principal principal) throws Exception;
//	public TaskResponseDto removeEmployeeFromTask(List<Integer> empId , List<>)

}
