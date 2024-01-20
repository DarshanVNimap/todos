package com.todoApp.config;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.todoApp.entity.Employee;

public class CustomeUserDetail implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -727841854923561346L;

	private final Employee employee;

	private  Set<SimpleGrantedAuthority> authorities1;

	public CustomeUserDetail(Employee e) {
		this.employee = e;
	}
	
	
	

	public CustomeUserDetail(Employee employee, Set<SimpleGrantedAuthority> authorities1) {
		this.employee = employee;
		this.authorities1 = authorities1;
	}




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities1;
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

