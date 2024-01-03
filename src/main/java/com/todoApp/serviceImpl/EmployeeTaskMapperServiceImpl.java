package com.todoApp.serviceImpl;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.todoApp.dto.ResponseDto;
import com.todoApp.dto.TaskAssignDto;
import com.todoApp.entity.Employee;
import com.todoApp.entity.EmployeeTaskMapper;
import com.todoApp.entity.Task;
import com.todoApp.entity.TaskStatus;
import com.todoApp.exceptions.TaskNotFoundException;
import com.todoApp.repository.EmployeeRepository;
import com.todoApp.repository.EmployeeTaskMapperRepository;
import com.todoApp.repository.TaskRepository;
import com.todoApp.service.AppService;
import com.todoApp.service.EmployeeTaskMapperService;

@Service
public class EmployeeTaskMapperServiceImpl implements EmployeeTaskMapperService {

	@Autowired
	private EmployeeTaskMapperRepository empTRepo;

	@Autowired
	private AppService appService;

	@Autowired
	private TaskRepository taskRepo;
	
	private EmployeeRepository empRepo;

	@Override
	public List<EmployeeTaskMapper> getAllAssignedTask(Principal principal) {
		return empTRepo.findByEmployee(appService.getEmployee(principal));
	}

	@Override
	public List<EmployeeTaskMapper> getAllEmpployeeAssignedTask(Integer taskId, Principal principal) {

		Task task = taskRepo.findByCreatedByAndId(appService.getEmployee(principal), taskId)
				.orElseThrow(() -> new TaskNotFoundException("Task not exist!!"));
		
		return empTRepo.findByTask(task);

	}

	@Override
	public ResponseDto assignTask(TaskAssignDto assignDto , Principal principal) throws Exception {
		
		Task task = taskRepo.findByCreatedByAndId(appService.getEmployee(principal), assignDto.getTaskId())
				.orElseThrow(() -> new TaskNotFoundException("Task not exist!!"));
		
		for(int i : assignDto.getEmpIds()) {
			
			Employee e = empRepo.findById(i).orElseThrow(() -> new Exception("Employee not exist!! with id: "+ i));
			
			empTRepo.save(EmployeeTaskMapper.builder()
			                  .employee(e)
			                  .task(task)
			                  .status(TaskStatus.TO_DO)
							  .build());
		}
		return ResponseDto.builder()
						  .message("Task has been assigned!!")
						  .status(HttpStatus.OK)
						  .time(new Date())
				          .build();
	}
	
	public ResponseDto withdrawlTask(TaskAssignDto assignDto , Principal principal) throws Exception {
		Task task = taskRepo.findByCreatedByAndId(appService.getEmployee(principal), assignDto.getTaskId())
				.orElseThrow(() -> new TaskNotFoundException("Task not exist!!"));
		
		for(int i : assignDto.getEmpIds()) {
			Employee e = empRepo.findById(i).orElseThrow(() -> new Exception("Employee not exist!! with id: "+ i));
			empTRepo.deleteByEmployeeAndTask(e, task);
		}
		return ResponseDto.builder()
				  .message("Task has been withdraw!!")
				  .status(HttpStatus.OK)
				  .time(new Date())
		          .build();
		
	}
	
	
	
	



}