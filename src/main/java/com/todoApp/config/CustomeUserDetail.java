package com.todoApp.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.todoApp.entity.Employee;
import com.todoApp.entity.Role;
import com.todoApp.entity.RolePermissionMapper;
import com.todoApp.repository.RolePermissionMapperRepository;

public class CustomeUserDetail implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -727841854923561346L;

	private final Employee employee;

	private RolePermissionMapperRepository mapperRepo;

	public CustomeUserDetail(Employee e) {
		this.employee = e;
	}
	
	
	

	public CustomeUserDetail(Employee employee, RolePermissionMapperRepository mapperRepo) {
		this.employee = employee;
		this.mapperRepo = mapperRepo;
	}




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities1 = new HashSet<>();
		
		Role role = employee.getRole();
		
		authorities1.add(new SimpleGrantedAuthority("ROLE_"+role.getRole()));
		
		for (RolePermissionMapper map : mapperRepo.findByRole(role)) {
			authorities1.add(new SimpleGrantedAuthority(map.getPermission().getAction()));
		}

		
		return authorities1;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return employee.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

