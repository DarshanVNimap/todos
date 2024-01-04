package com.todoApp.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.todoApp.dto.PermissionDto;
import com.todoApp.dto.ResponseDto;
import com.todoApp.entity.Permission;
import com.todoApp.exceptions.PermissionNotFoundException;
import com.todoApp.repository.PermissionRepository;

@Service
public class PermissionService {

	@Autowired
	private PermissionRepository permissionRepo;
	
	@Autowired
	private ModelMapper modelMapper;


	public List<Permission> getAllPermission() {
		return permissionRepo.findAll();
	}

	public ResponseDto addPermission(PermissionDto permissionDto) {
       
		permissionRepo.save(modelMapper.map(permissionDto, Permission.class));

		return ResponseDto.builder().message("Permission added!!").status(HttpStatus.CREATED).time(new Date()).build();
	}

	public ResponseDto deletePermission(Integer permissionId) {
		
		permissionRepo.findById(permissionId).orElseThrow(() -> new PermissionNotFoundException("Permission not found!!"));
		permissionRepo.deleteById(permissionId);
		return ResponseDto.builder().message("Permission removed!!").status(HttpStatus.OK).time(new Date()).build();
	}

	public ResponseDto updatePermission(Integer permissionId, PermissionDto permissiondto) {

		Permission permission = modelMapper.map(permissiondto, Permission.class);
		permission.setId(permissionId);
		permissionRepo.save(permission);
		return ResponseDto.builder().message("Permission updated!!").status(HttpStatus.OK).time(new Date()).build();
	}

}
