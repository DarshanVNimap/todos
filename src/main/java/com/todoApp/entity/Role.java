package com.todoApp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String role;
	
	
	@OneToMany(cascade =CascadeType.ALL , fetch = FetchType.EAGER , mappedBy = "role")
	@JsonManagedReference
	private List<Employee> employee;
	
	@OneToMany(cascade = CascadeType.MERGE , mappedBy = "role")
	@JsonManagedReference
	private List<RolePermissionMapper> rolePermissionMapper;

}
