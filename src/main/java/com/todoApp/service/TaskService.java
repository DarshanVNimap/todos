package com.todoApp.service;

import java.security.Principal;
import java.util.List;

import com.todoApp.dto.ResponseDto;
import com.todoApp.dto.TaskDto;
import com.todoApp.dto.TaskResponseDto;

public interface TaskService {
	
	public List<TaskResponseDto> getAllCreatedTasks(Principal principal) throws Exception;
	
	public TaskResponseDto getTaskById(Principal principal , Integer taskId) throws Exception; 
	
	public ResponseDto addTask(TaskDto task , Principal principal) throws Exception ;
	
	public ResponseDto updateTask(Integer id , TaskDto task , Principal principal) throws Exception ;
	
	public ResponseDto deleteTask(Integer id);
	
//	public List<TaskResponseDto> filteredTaskBasedonStatus(TaskStatus status , Principal principa);

}
