package com.todoApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoApp.entity.Employee;
import com.todoApp.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	public Optional<List<Task>> findByCreatedBy(Employee employee);

	public Optional<Task> findByCreatedByAndId(Employee employee , Integer id);
	

}
