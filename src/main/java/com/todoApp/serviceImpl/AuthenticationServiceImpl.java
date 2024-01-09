package com.todoApp.serviceImpl;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.todoApp.config.CustomeUserDetail;
import com.todoApp.dto.EmployeeDto;
import com.todoApp.dto.LoginDto;
import com.todoApp.dto.TokenResponse;
import com.todoApp.entity.Employee;
import com.todoApp.entity.Role;
import com.todoApp.exceptions.EmailAlreadyExistException;
import com.todoApp.jwtUtils.JwtAuthService;
import com.todoApp.repository.EmployeeRepository;
import com.todoApp.repository.RoleRepository;
import com.todoApp.service.AuthenticationService;


@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private JwtAuthService jwtService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public TokenResponse register(EmployeeDto employeeDto) throws Exception {
		
		Role role = roleRepo.findById(2).orElseThrow(() -> new Exception("Role does not exist!!"));
		
		if(empRepo.existsByEmail(employeeDto.getEmail())) {
			throw new EmailAlreadyExistException("Email already Exists");
		}
		
		Employee e = modelMapper.map(employeeDto, Employee.class);
		e.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
		e.setRegisterAt(new Date());
		e.setRole(role);
		
		empRepo.save(e);
		
		return TokenResponse.builder()
							.jwtToken(jwtService.generateToken(new CustomeUserDetail(e)))
							.userName(e.getEmail())
							.msg("Registered Successfully!!")
							.build();
	}

	@Override
	public TokenResponse login(LoginDto loginDto) throws UserPrincipalNotFoundException, AuthenticationException {
		
//		System.out.println(loginDto.getEmail() +" "+loginDto.getPassword());
		
		authManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
				);
		
		Employee emp = empRepo.findByEmail(
								loginDto.getEmail())
											.orElseThrow(() -> new  UserPrincipalNotFoundException("Invalid username or password!!"));
		
		String jwtToken = jwtService.generateToken(new CustomeUserDetail(emp));
		System.err.println("db connection");
		return TokenResponse.builder()
							.jwtToken(jwtToken)
							.userName(emp.getEmail())
							.msg("Login success!!")
				            .build();
	}

}
