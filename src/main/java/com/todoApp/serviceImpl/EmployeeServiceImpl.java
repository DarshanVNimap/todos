package com.todoApp.serviceImpl;

import java.security.Principal;
import java.util.ArrayList;
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
	
//	@Autowired
//	private AppService appService;
//	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<EmployeeResponseDto> getAllEmployee(Principal principal) {
		
		List<EmployeeResponseDto> employee = new ArrayList<>();
		
		Employee e = empRepo.findByEmail(principal.getName()).get();
		
		if(e.getRole().getRole().equals("ADMIN")) {
			employee =  empRepo.findAll().stream().map( em -> mapper.map(em, EmployeeResponseDto.class)).toList();
		}
		else {
			employee.add(mapper.map(empRepo.findById(e.getId()).get(), EmployeeResponseDto.class));
		}
		
		
		return employee;
	}

	@Override
	public ResponseDto addEmployee(Employee employee) {
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
	public EmployeeResponseDto getEmployeeById(Integer empId) {
		Employee emp = empRepo.findById(empId).orElseThrow(() -> new EmployeeNotFoundException("Exployee not exist "));
		return mapper.map(emp, EmployeeResponseDto.class);
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
