package com.todoApp.service;

import java.security.Principal;
import java.util.List;

import com.todoApp.dto.ResponseDto;
import com.todoApp.dto.TaskAssignDto;
import com.todoApp.entity.EmployeeTaskMapper;

public interface EmployeeTaskMapperService {
	
	public List<EmployeeTaskMapper> getAllAssignedTask(Principal principal);
	
	public List<EmployeeTaskMapper> getAllEmpployeeAssignedTask(Integer taskId , Principal principal);
	
	public ResponseDto assignTask(TaskAssignDto assignDto ,Principal principal) throws Exception;
	
	public ResponseDto withdrawlTask(TaskAssignDto assignDto, Principal principal) throws Exception ;
	
	public ResponseDto updateTaskStatus(Integer taskId ,Principal principal);
	
//	public ResponseDto update(List<Integer> empId , Integer taskId ,Principal principal) throws Exception;
//	public TaskResponseDto removeEmployeeFromTask(List<Integer> empId , List<>)

}
