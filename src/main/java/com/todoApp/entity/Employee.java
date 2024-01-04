package com.todoApp.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String email;
	private String password;
	
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JsonBackReference
	private Role role;
	
	@OneToMany(cascade = CascadeType.MERGE , mappedBy = "createdBy")
	@JsonManagedReference
	private List<Task> createdTasks;
	
	@OneToMany(mappedBy = "employee" , cascade = CascadeType.MERGE)
	@JsonManagedReference
	private List<EmployeeTaskMapper> assignedTo;
	
	private Date registerAt;

}
