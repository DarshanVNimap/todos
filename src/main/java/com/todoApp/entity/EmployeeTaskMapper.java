package com.todoApp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeTaskMapper {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JsonBackReference
	private Employee employee;
	
	@ManyToOne
	@JsonBackReference
	private Task task;
	
	@Enumerated(EnumType.ORDINAL)
	private TaskStatus status;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "taskMapper")
	private List<TaskHistory> taskHistory;
	
	@UpdateTimestamp
	private LocalDateTime UpdatedAt;
	
	@CreationTimestamp
	private LocalDateTime assignAt;
	
	

}
