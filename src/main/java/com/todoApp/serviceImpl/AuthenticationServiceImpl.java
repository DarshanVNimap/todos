package com.todoApp.serviceImpl;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todoApp.Utils.ErrorMessageConstant;
import com.todoApp.Utils.SuccessMessageConstant;
import com.todoApp.config.CustomeUserDetail;
import com.todoApp.dto.EmployeeDto;
import com.todoApp.dto.LoginDto;
import com.todoApp.dto.ResponseDto;
import com.todoApp.dto.TokenResponse;
import com.todoApp.entity.Employee;
import com.todoApp.entity.Role;
import com.todoApp.exceptions.EmailAlreadyExistException;
import com.todoApp.exceptions.RoleNotFoundException;
import com.todoApp.repository.EmployeeRepository;
import com.todoApp.repository.RoleRepository;
import com.todoApp.service.AuthenticationService;
import com.todoApp.service.JwtAuthService;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

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
	public ResponseDto register(EmployeeDto employeeDto) throws Exception {

		Role role = roleRepo.findById(2).orElseThrow(() -> new RoleNotFoundException(ErrorMessageConstant.ROLE_NOT_FOUND));

		if (empRepo.existsByEmail(employeeDto.getEmail())) {
			throw new EmailAlreadyExistException(ErrorMessageConstant.EMAIL_EXIST);
		}

		Employee e = modelMapper.map(employeeDto, Employee.class);
		e.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
		e.setRegisterAt(new Date());

		e.setRole(role);

		empRepo.save(e);

		return ResponseDto.builder().message(SuccessMessageConstant.REGISTRATION_SUCCESS).status(HttpStatus.CREATED)
				.time(new Date()).build();
	}

	@Override
	public TokenResponse login(LoginDto loginDto) throws UserPrincipalNotFoundException, AuthenticationException {

		authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

		Employee emp = empRepo.findByEmail(loginDto.getEmail()).orElseThrow(
				() -> new UserPrincipalNotFoundException(ErrorMessageConstant.INVALID_USERNAME_OR_PASSWORD));

		String jwtToken = jwtService.generateToken(new CustomeUserDetail(emp));

		return TokenResponse.builder().jwtToken(jwtToken).userName(emp.getEmail())
				.msg(SuccessMessageConstant.LOGIN_SUCCESS).build();
	}

}
