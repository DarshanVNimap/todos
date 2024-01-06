package com.todoApp.service;

import java.security.Principal;
import java.util.List;

import com.todoApp.dto.EmployeeResponseDto;
import com.todoApp.dto.ResponseDto;
import com.todoApp.entity.Employee;

public interface EmployeeService {
	
	public List<EmployeeResponseDto> getAllEmployee(Principal principal);
	
	public EmployeeResponseDto getEmployeeById(Integer empId);
	
	public ResponseDto addEmployee(Employee employee);
	
	public ResponseDto removeEmployee(Integer employee);
	
	
//	public ResponseDto updateEmployeeDetail(EmployeeDto employeeDto , Principal principal) throws Exception;
	

}
