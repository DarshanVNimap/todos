package com.todoApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoApp.entity.Employee;
import com.todoApp.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	public Optional<List<Task>> findByCreatedBy(Employee employee);

	public Optional<Task> findByCreatedByAndId(Employee employee , Integer id);
	
//	@Query(value = "UPDATE from task set status = ?1 WHERE end_at < date && status != ?2" , nativeQuery = true)
//	public void updateStatusEndAtBeforeAndStatusNot(LocalDate date , short status);
	

}
