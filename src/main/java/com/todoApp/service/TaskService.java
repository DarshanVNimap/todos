package com.todoApp.service;

import java.security.Principal;
import java.util.List;

import com.todoApp.dto.TaskDto;
import com.todoApp.dto.ResponseDto;
import com.todoApp.entity.Task;

public interface TaskService {
	
	public List<Task> getAllTask(Principal principal) throws Exception;
	
	public ResponseDto addTask(TaskDto task , Principal principal) throws Exception ;
	
	public ResponseDto updateTask(Integer id , TaskDto task , Principal principal) throws Exception ;
	
	public ResponseDto deleteTask(Integer id);

}