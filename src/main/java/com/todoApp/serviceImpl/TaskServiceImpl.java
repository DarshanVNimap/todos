package com.todoApp.serviceImpl;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.todoApp.dto.ResponseDto;
import com.todoApp.dto.TaskDto;
import com.todoApp.dto.TaskResponseDto;
import com.todoApp.entity.Employee;
import com.todoApp.entity.Task;
import com.todoApp.exceptions.TaskNotFoundException;
import com.todoApp.repository.TaskRepository;
import com.todoApp.service.AppService;
import com.todoApp.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private AppService appService;

	@Autowired
	private TaskRepository taskRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<TaskResponseDto> getAllCreatedTasks(Principal principal) throws Exception {
		Employee employee = appService.getEmployee(principal);

		List<Task> tasks = new ArrayList<>();

		if (employee.getRole().getRole().equals("ADMIN")) {
			tasks = taskRepo.findAll();
		} else {
			tasks = taskRepo.findByCreatedBy(employee)
					.orElseThrow(() -> new TaskNotFoundException("You have not created any task!!"));
		}
//        return tasks;
		return tasks.stream().map(t -> mapper.map(t, TaskResponseDto.class)).toList();
	}

	@Override
	public ResponseDto addTask(TaskDto taskdto, Principal principal) throws Exception {

		if (!taskdto.getStartAt().isBefore(taskdto.getEndAt())) {
			throw new Exception("Start date should be before end date");
		} else if (!taskdto.getStartAt().isAfter(LocalDate.now())) {
			throw new Exception("Start date should be after today's date");
		}

		Employee employee = appService.getEmployee(principal);

		Task task = mapper.map(taskdto, Task.class);

		task.setCreatedBy(employee);
		if (taskRepo.save(task) == null) {
			throw new Exception("Something went wrong!! task not added, try after sometime");
		}
		return ResponseDto.builder().message("Task created....").status(HttpStatus.CREATED).time(new Date()).build();
	}

	@Override
	public ResponseDto updateTask(Integer id, TaskDto taskdto, Principal principal) throws Exception {

		Employee employee = appService.getEmployee(principal);

		Task task = mapper.map(taskdto, Task.class);

		task.setCreatedBy(employee);
		task.setId(id);
		if (taskRepo.save(task) == null) {
			throw new Exception("Something went wrong!! task not added, try after sometime");
		}
		return ResponseDto.builder().message("Task updated....").status(HttpStatus.CREATED).time(new Date()).build();

	}

	@Override
	public ResponseDto deleteTask(Integer id) {

		taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

		taskRepo.deleteById(id);

		return ResponseDto.builder().message("Task removed....").status(HttpStatus.OK).time(new Date()).build();
	}

	@Override
	public TaskResponseDto getTaskById(Principal principal, Integer taskId) throws Exception {
		
		Employee employee = appService.getEmployee(principal);
		
		Task task = new Task();
		
		if (employee.getRole().getRole().equals("ADMIN")) {
			task = taskRepo.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found with id: "+taskId));
		} else {
			task = taskRepo.findByCreatedByAndId(employee , taskId)
					.orElseThrow(() ->new TaskNotFoundException("Task not found with id: "+taskId));
		}
		
		return mapper.map(task, TaskResponseDto.class);
	}

//	@Override
//	public List<TaskResponseDto> filteredTaskBasedonStatus(TaskStatus status , Principal principal) {
//		
//		Employee employee = appService.getEmployee(principal);
//		
//		return null;
//	}

}
