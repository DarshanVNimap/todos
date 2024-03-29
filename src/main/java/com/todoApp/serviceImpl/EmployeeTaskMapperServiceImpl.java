package com.todoApp.serviceImpl;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.todoApp.Utils.ErrorMessageConstant;
import com.todoApp.Utils.SuccessMessageConstant;
import com.todoApp.dto.EmployeeTaskMapperResponseDto;
import com.todoApp.dto.IFilterTaskDto;
import com.todoApp.dto.ResponseDto;
import com.todoApp.dto.TaskAssignDto;
import com.todoApp.entity.Employee;
import com.todoApp.entity.EmployeeTaskMapper;
import com.todoApp.entity.Task;
import com.todoApp.entity.TaskHistory;
import com.todoApp.entity.TaskStatus;
import com.todoApp.exceptions.EmployeeNotFoundException;
import com.todoApp.exceptions.TaskNotFoundException;
import com.todoApp.repository.EmployeeRepository;
import com.todoApp.repository.EmployeeTaskMapperRepository;
import com.todoApp.repository.TaskHistoryRepository;
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

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private TaskHistoryRepository taskHistoryRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<EmployeeTaskMapperResponseDto> getAllAssignedTask(Principal principal) {

		return empTRepo.findByEmployeeAndStatusNot(appService.getEmployee(principal) , TaskStatus.DONE).stream()
				.map(t -> mapper.map(t, EmployeeTaskMapperResponseDto.class)).toList();
	}

	@Override
	public List<EmployeeTaskMapperResponseDto> getAllEmpployeeAssignedTask(Integer taskId, Principal principal) {

		Task task = taskRepo.findByCreatedByAndId(appService.getEmployee(principal), taskId)
				.orElseThrow(() -> new TaskNotFoundException(ErrorMessageConstant.TASK_NOT_FOUND));

		return empTRepo.findByTask(task).stream().map(t -> mapper.map(t, EmployeeTaskMapperResponseDto.class)).toList();

	}

	@Override
	public ResponseDto assignTask(TaskAssignDto assignDto, Principal principal) throws Exception {

		Task task = taskRepo.findByCreatedByAndId(appService.getEmployee(principal), assignDto.getTaskId())
				.orElseThrow(() -> new TaskNotFoundException(ErrorMessageConstant.TASK_NOT_FOUND));

		for (int i : assignDto.getEmpIds()) {

			Employee e = empRepo.findById(i).orElseThrow(() -> new EmployeeNotFoundException(ErrorMessageConstant.EMPLOYEE_NOT_FOUND));

			EmployeeTaskMapper emapper = empTRepo
					.save(EmployeeTaskMapper.builder().employee(e).task(task).status(TaskStatus.TO_DO).build());

			taskHistoryRepo.save(TaskHistory.builder().status(TaskStatus.TO_DO).updatedBy(e.getId()).taskMapper(emapper)
					.assignAt(emapper.getAssignAt()).build());
		}
		return ResponseDto.builder().message(SuccessMessageConstant.TASK_ASSGINED).status(HttpStatus.OK).time(new Date()).build();
	}

	public ResponseDto withdrawlTask(TaskAssignDto assignDto, Principal principal) throws Exception {
		Task task = taskRepo.findByCreatedByAndId(appService.getEmployee(principal), assignDto.getTaskId())
				.orElseThrow(() -> new TaskNotFoundException(ErrorMessageConstant.TASK_NOT_FOUND));

		for (int i : assignDto.getEmpIds()) {
			Employee e = empRepo.findById(i).orElseThrow(() -> new EmployeeNotFoundException(ErrorMessageConstant.EMPLOYEE_NOT_FOUND));
			empTRepo.deleteByEmployeeAndTask(e, task);
		}
		return ResponseDto.builder().message("Task has been withdraw!!").status(HttpStatus.OK).time(new Date()).build();

	}

	@Override
	public ResponseDto updateTaskStatus(Integer taskId, Principal principal) throws Exception {

		Employee employee = appService.getEmployee(principal);
		Task task = taskRepo.findById(taskId).orElseThrow(() -> new TaskNotFoundException(ErrorMessageConstant.TASK_NOT_FOUND));
		EmployeeTaskMapper assignedTask = empTRepo.findByEmployeeAndTask(employee, task)
				.orElseThrow(() -> new Exception(ErrorMessageConstant.TASK_NOT_ASSIGNED));

		if (assignedTask.getStatus() == TaskStatus.DONE) {
			return ResponseDto.builder().message("task already completed!!").status(HttpStatus.OK).time(new Date())
					.build();

		}

		TaskStatus status = null;

		if (task.getEndAt().isAfter(LocalDate.now())) {
			if (assignedTask.getStatus() == TaskStatus.TO_DO) {
				status = TaskStatus.IN_PROGRESS;
			} else if (assignedTask.getStatus() == TaskStatus.IN_PROGRESS) {
				status = TaskStatus.DONE;
			}
		} else {
			return ResponseDto.builder().message("task time exceeds!!").status(HttpStatus.OK).time(new Date()).build();
		}

		assignedTask.setStatus(status);

		EmployeeTaskMapper emapper = empTRepo.save(assignedTask);

		if (emapper != null) {

			taskHistoryRepo.save(TaskHistory.builder().status(status).updatedBy(employee.getId()).taskMapper(emapper)
					.assignAt(emapper.getAssignAt()).build());

		}

		return ResponseDto.builder().message(SuccessMessageConstant.TASK_STATUS_UPDATED).status(HttpStatus.OK).time(new Date()).build();

	}

	@Override
	public List<IFilterTaskDto> filterAssignTask(TaskStatus status, LocalDate start_date,
			LocalDate end_date, Principal principal) {
		Employee emp = appService.getEmployee(principal);
		
		return empTRepo.filterAssignedTask((short)status.ordinal(), start_date, end_date, emp.getId())
				;

//		return null;
	}

}
