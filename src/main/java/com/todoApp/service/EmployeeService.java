package com.todoApp.service;

import java.security.Principal;
import java.util.List;

import javax.management.relation.RoleNotFoundException;

import com.todoApp.dto.AdminEmployeeDto;
import com.todoApp.dto.EmployeeResponseDto;
import com.todoApp.dto.ResponseDto;

public interface EmployeeService {
	
	public List<EmployeeResponseDto> getAllEmployee(Principal principal);
	
	public EmployeeResponseDto getEmployeeById(Integer empId);
	
	public ResponseDto addEmployee(AdminEmployeeDto employeeDto) throws RoleNotFoundException, Exception;
	
	public ResponseDto removeEmployee(Integer employee);
	
	
//	public ResponseDto updateEmployeeDetail(EmployeeDto employeeDto , Principal principal) throws Exception;
	

}
