package com.todoApp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String action;
	
	@OneToMany(cascade = { CascadeType.MERGE,CascadeType.PERSIST }, mappedBy =  "permission")
	@JsonManagedReference
	private List<RolePermissionMapper> rolePermissionMapper;

}
