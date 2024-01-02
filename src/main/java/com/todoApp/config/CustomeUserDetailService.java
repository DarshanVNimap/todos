package com.todoApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.todoApp.entity.Employee;
import com.todoApp.repository.EmployeeRepository;
import com.todoApp.repository.RolePermissionMapperRepository;

public class CustomeUserDetailService implements UserDetailsService{
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private RolePermissionMapperRepository rolePermissionRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Employee emp = empRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Employee not found with email: "+username));
		
		return new CustomeUserDetail(emp , rolePermissionRepo);
	}

}
