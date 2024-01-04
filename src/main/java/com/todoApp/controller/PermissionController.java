package com.todoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoApp.dto.PermissionDto;
import com.todoApp.entity.Permission;
import com.todoApp.service.PermissionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/permission")
@SecurityRequirement(name = "jwt")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;
	
	@GetMapping
	public ResponseEntity<List<Permission>> getAllPermission(){
		return ResponseEntity.ok(permissionService.getAllPermission());
	}
	
	@PostMapping()
	public ResponseEntity<?> addPermission(@RequestBody PermissionDto permissions){
		return ResponseEntity.ok(permissionService.addPermission(permissions));
	}
	
	@DeleteMapping("/{permissionId}")
	public ResponseEntity<?> removePermissionOfRole(@PathVariable(name = "permissionId") Integer permissionId){
		return ResponseEntity.ok(permissionService.deletePermission(permissionId));
	}
	
	@PutMapping("/{permissionId}")
	public ResponseEntity<?> updatePermission(@PathVariable(name = "permissionId") Integer permissionId ,@RequestBody PermissionDto permission){
		return ResponseEntity.ok(permissionService.updatePermission(permissionId , permission));
	}
	

}
