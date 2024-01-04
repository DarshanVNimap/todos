package com.todoApp.controller;

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

import com.todoApp.entity.Role;
import com.todoApp.service.RoleService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/role")
@SecurityRequirement(name = "jwt")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
	
	
	@Autowired
	private RoleService roleService;
	
	
	@GetMapping
	public ResponseEntity<?> getAllRole(){
		return ResponseEntity.ok(roleService.getAllRoles());
	}
	
	@GetMapping("/{roleId}")
	public ResponseEntity<?> getRoleById(@PathVariable(name = "roleId") Integer id) throws Exception {
		return ResponseEntity.ok(roleService.getAllRoles());
	}
	
	@PostMapping()
	public ResponseEntity<?> addRole(@RequestBody Role role) {
		return ResponseEntity.ok(roleService.addRole(role));
	}
	
	@PutMapping("/{roleId}")
	public ResponseEntity<Role> updateRole(@RequestBody Role role ,@PathVariable(name = "roleId") Integer id) {
		return ResponseEntity.ok(roleService.updateRole(id, role));
	}
	
	@DeleteMapping("/{roleId}")
	public ResponseEntity<String> deleteRole(@PathVariable(name = "roleId") Integer id) {
		return ResponseEntity.ok(roleService.deleteRoll(id)) ;
	}
	
	
}
