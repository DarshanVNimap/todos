package com.todoApp.serviceImpl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.relation.RoleNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.todoApp.Utils.ErrorMessageConstant;
import com.todoApp.Utils.SuccessMessageConstant;
import com.todoApp.dto.AdminEmployeeDto;
import com.todoApp.dto.EmployeeResponseDto;
import com.todoApp.dto.ResponseDto;
import com.todoApp.entity.Employee;
import com.todoApp.entity.Role;
import com.todoApp.exceptions.EmployeeNotFoundException;
import com.todoApp.repository.EmployeeRepository;
import com.todoApp.repository.RoleRepository;
import com.todoApp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private ModelMapper mapper;

//	@Autowired
//	private AppService appService;

	@Autowired
	private RoleRepository roleRepo;
//	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<EmployeeResponseDto> getAllEmployee(Principal principal) {

		List<EmployeeResponseDto> employee = new ArrayList<>();
		employee = empRepo.findAll().stream().map(em -> mapper.map(em, EmployeeResponseDto.class)).toList();
		return employee;
	}

	@Override
	public ResponseDto removeEmployee(Integer employee) {

		empRepo.findById(employee)
				.orElseThrow(() -> new EmployeeNotFoundException(ErrorMessageConstant.EMPLOYEE_NOT_FOUND));

		empRepo.deleteById(employee);
		return ResponseDto.builder().message(SuccessMessageConstant.EMPLOYEE_DELETED).status(HttpStatus.OK).time(new Date()).build();
	}

	@Override
	public EmployeeResponseDto getEmployeeById(Integer empId) {
		Employee emp = empRepo.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(ErrorMessageConstant.EMPLOYEE_NOT_FOUND));
		return mapper.map(emp, EmployeeResponseDto.class);
	}

	@Override
	public ResponseDto addEmployee(AdminEmployeeDto employeeDto) throws Exception {
		Role getRole = roleRepo.findById(employeeDto.getRoleId())
				.orElseThrow(() -> new RoleNotFoundException(ErrorMessageConstant.ROLE_NOT_FOUND));
		Employee emp = mapper.map(employeeDto, Employee.class);
		emp.setRole(getRole);
		emp.setPassword(passwordEncoder.encode(employeeDto.getPassword()));

		if (empRepo.save(emp) == null) {
			throw new Exception(ErrorMessageConstant.SOMETHING_WENT_WRONG);
		}
		return ResponseDto.builder().message(SuccessMessageConstant.EMPLOYEE_ADDED).status(HttpStatus.OK).time(new Date()).build();
	}

//	@Override
//	public ResponseDto updateEmployeeDetail(EmployeeDto employeeDto, Principal principal) throws Exception {
//			Employee e = appService.getEmployee(principal);
//			
//			Employee employee = mapper.map(employeeDto, Employee.class);
//			
//			employee.setId(e.getId());
//			employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
//			employee.setRegisterAt(e.getRegisterAt());
//			employee.setRole(e.getRole());
//			
//			if(empRepo.save(employee) == null) {
//				throw new Exception("Something went wrong!!  try after sometime");
//			}
//			
//			return ResponseDto.builder()
//			          .message("Detail updated!!")
//			          .status(HttpStatus.OK)
//			          .time(new Date())
//					  .build();
//			
//	}

}
