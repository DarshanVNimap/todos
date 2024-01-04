package com.todoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoApp.dto.RolePermissionResponseDto;
import com.todoApp.service.RolePermissionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/rolePermission")
@SecurityRequirement(name = "jwt")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class RolePermissionController {
	
	@Autowired
	private RolePermissionService rolePermissionService;

//	@GetMapping
//	public ResponseEntity<?> getAllRoleWithPermission(){
////		rolePermissionRepo.findAll().stream().map( m ->
////				RolePermissionResponseDto.builder().role(null).action(m.getPermission().getAction()).build()
////				).toList();
//		
////		for()
//	RolePermissionResponseDto r=new RolePermissionResponseDto();
//		List<RolePermissionMapper> list= 	rolePermissionRepo.findAll();
//	for(RolePermissionMapper rolePermission:list) {
//		rolePermission.getPermission().getAction();
//	}
//		return ResponseEntity.ok(rolePermissionRepo.findAll());
//	}
	
	@PostMapping
	public ResponseEntity<?> assignPermissionToRole(RolePermissionResponseDto rolePermission) throws Exception{
		return ResponseEntity.ok(rolePermissionService.assignPermissionToRole(rolePermission));
	}
	
	@DeleteMapping
	public ResponseEntity<?> removePermissionToRole(RolePermissionResponseDto rolePermission) throws Exception{
		return ResponseEntity.ok(rolePermissionService.withdrawPermissionToRole(rolePermission));
	}
	
}
