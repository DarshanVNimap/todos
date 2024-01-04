package com.todoApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoApp.entity.Role;
import com.todoApp.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepo;
	
	public List<Role> getAllRoles(){
		return roleRepo.findAll(); 
	}

	public Role getRole(Integer id) throws Exception {
		return roleRepo.findById(id).orElseThrow(() -> new Exception("Role not found!!"));
	}
	
	public Role addRole(Role role) {
		return roleRepo.save(role);
	}
	
	public Role updateRole(Integer id , Role role) {
	
		role.setId(id);
		return roleRepo.save(role);
	}
	
	public String deleteRoll(Integer id) {
		
		roleRepo.deleteById(id);
		return "Role removed!!";
	}
}
