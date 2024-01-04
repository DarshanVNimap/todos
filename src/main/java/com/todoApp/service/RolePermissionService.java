package com.todoApp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.todoApp.dto.ResponseDto;
import com.todoApp.dto.RolePermissionResponseDto;
import com.todoApp.entity.Permission;
import com.todoApp.entity.Role;
import com.todoApp.entity.RolePermissionMapper;
import com.todoApp.repository.PermissionRepository;
import com.todoApp.repository.RolePermissionMapperRepository;
import com.todoApp.repository.RoleRepository;

@Service
public class RolePermissionService {

	@Autowired
	private RolePermissionMapperRepository rolePermissionRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PermissionRepository permissionRepo;
	
	public ResponseDto assignPermissionToRole(RolePermissionResponseDto rolePermissionDto) throws Exception {
		Role role = roleRepo.findById(rolePermissionDto.getRoleId()).orElseThrow(() -> new Exception("Role not found!!"));
		Permission permission = permissionRepo.findById(rolePermissionDto.getPermissionId()).orElseThrow(() -> new Exception("Role not found!!"));
		
		rolePermissionRepo.save(RolePermissionMapper.builder().role(role).permission(permission).build());
		return ResponseDto.builder().message("Permission assigned!!").status(HttpStatus.OK).time(new Date()).build();
	}
	
	public ResponseDto withdrawPermissionToRole(RolePermissionResponseDto rolePermissionDto) throws Exception {
		Role role = roleRepo.findById(rolePermissionDto.getRoleId()).orElseThrow(() -> new Exception("Role not found!!"));
		Permission permission = permissionRepo.findById(rolePermissionDto.getPermissionId()).orElseThrow(() -> new Exception("Role not found!!"));
		
		rolePermissionRepo.deleteByRoleAndPermission(role, permission);
		return ResponseDto.builder().message("Permission removed!!").status(HttpStatus.OK).time(new Date()).build();
	}
}
