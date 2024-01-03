package com.todoApp.serviceImpl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.todoApp.dto.EmployeeResponseDto;
import com.todoApp.dto.ResponseDto;
import com.todoApp.entity.Employee;
import com.todoApp.exceptions.EmployeeNotFoundException;
import com.todoApp.repository.EmployeeRepository;
import com.todoApp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<EmployeeResponseDto> getAllEmployee() {
		
		return empRepo.findAll().stream().map( e -> mapper.map(e, EmployeeResponseDto.class)).toList();
	}

	@Override
	public ResponseDto addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto removeEmployee(Integer employee) {

		empRepo.findById(employee)
				.orElseThrow(() -> new EmployeeNotFoundException("Exployee not exist or already removed..."));

		empRepo.deleteById(employee);
		return ResponseDto.builder()
				          .message("Employee removed!!")
				          .status(HttpStatus.OK)
				          .time(new Date())
						  .build();
	}

	@Override
	public ResponseDto updateEmployee(Integer id, Employee employee) {
		
		
		return null;
	}

}
