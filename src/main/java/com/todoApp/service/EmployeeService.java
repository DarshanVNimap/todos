package com.todoApp.service;

import java.util.List;

import com.todoApp.dto.EmployeeResponseDto;
import com.todoApp.dto.ResponseDto;
import com.todoApp.entity.Employee;

public interface EmployeeService {
	
	public List<EmployeeResponseDto> getAllEmployee();
	
	public ResponseDto addEmployee(Employee employee);
	
	public ResponseDto removeEmployee(Integer employee);
	
	public ResponseDto updateEmployee(Integer id , Employee employee);

}
