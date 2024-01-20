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

import com.todoApp.Utils.ErrorMessageConstant;
import com.todoApp.Utils.SuccessMessageConstant;
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
	
//	@Scheduled(fixedRate = 24*60*60*1000)
//	public void changeStatusToOverdueTask() {
//	 taskRepo.updateStatusEndAtBeforeAndStatusNot(LocalDate.now(), (short) 3 );
//		
//	}

	@Override
	public List<TaskResponseDto> getAllCreatedTasks(Principal principal) throws Exception {
		Employee employee = appService.getEmployee(principal);

		List<Task> tasks = new ArrayList<>();

		if (employee.getRole().getRole().equals("ADMIN")) {
			tasks = taskRepo.findAll();
		} else {
			tasks = taskRepo.findByCreatedBy(employee)
					.orElseThrow(() -> new TaskNotFoundException(ErrorMessageConstant.TASK_NOT_FOUND));
		}
//        return tasks;
		return tasks.stream().map(t -> mapper.map(t, TaskResponseDto.class)).toList();
	}

	@Override
	public ResponseDto addTask(TaskDto taskdto, Principal principal) throws Exception {

		if (!taskdto.getStartAt().isBefore(taskdto.getEndAt())) {
			throw new Exception(ErrorMessageConstant.INVALID_END_DATE);
		} else if (!taskdto.getStartAt().isAfter(LocalDate.now())) {
			throw new Exception(ErrorMessageConstant.INVALID_START_DATE);
		}

		Employee employee = appService.getEmployee(principal);

		Task task = mapper.map(taskdto, Task.class);

		task.setCreatedBy(employee);
		if (taskRepo.save(task) == null) {
			throw new Exception(ErrorMessageConstant.TASK_NOT_CREATED);
		}
		return ResponseDto.builder().message(SuccessMessageConstant.TASK_ADDED).status(HttpStatus.CREATED).time(new Date()).build();
	}

	@Override
	public ResponseDto updateTask(Integer id, TaskDto taskdto, Principal principal) throws Exception {

		Employee employee = appService.getEmployee(principal);

		Task task = mapper.map(taskdto, Task.class);

		task.setCreatedBy(employee);
		task.setId(id);
		if (taskRepo.save(task) == null) {
			throw new Exception(ErrorMessageConstant.TASK_NOT_UPDATED);
		}
		return ResponseDto.builder().message(SuccessMessageConstant.TASK_UPDATED).status(HttpStatus.CREATED).time(new Date()).build();

	}

	@Override
	public ResponseDto deleteTask(Integer id) {

		taskRepo.findById(id).orElseThrow(() -> new TaskNotFoundException(ErrorMessageConstant.TASK_NOT_FOUND));

		taskRepo.deleteById(id);

		return ResponseDto.builder().message(SuccessMessageConstant.TASK_DELETED).status(HttpStatus.OK).time(new Date()).build();
	}

	@Override
	public TaskResponseDto getTaskById(Principal principal, Integer taskId) throws Exception {
		
		Employee employee = appService.getEmployee(principal);
		
		Task task = new Task();
		
		if (employee.getRole().getRole().equals("ADMIN")) {
			task = taskRepo.findById(taskId).orElseThrow(() -> new TaskNotFoundException(ErrorMessageConstant.TASK_NOT_FOUND));
		} else {
			task = taskRepo.findByCreatedByAndId(employee , taskId)
					.orElseThrow(() ->new TaskNotFoundException(ErrorMessageConstant.TASK_NOT_FOUND));
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
