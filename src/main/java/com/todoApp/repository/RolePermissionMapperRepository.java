package com.todoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoApp.entity.Permission;
import com.todoApp.entity.Role;
import com.todoApp.entity.RolePermissionMapper;

public interface RolePermissionMapperRepository extends JpaRepository<RolePermissionMapper, Integer> {

	public RolePermissionMapper findByRoleAndPermission(Role role , Permission permission);
	
	public void deleteByRoleAndPermission(Role role , Permission permission);
	
	public List<RolePermissionMapper> findByRole(Role role);
	
}
