package com.todoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoApp.entity.Employee;
import com.todoApp.entity.EmployeeTaskMapper;
import com.todoApp.entity.Task;

public interface EmployeeTaskMapperRepository extends JpaRepository<EmployeeTaskMapper, Integer>{
	
	public List<EmployeeTaskMapper> findByEmployee(Employee employee);
	
	public List<EmployeeTaskMapper> findByTask(Task task);
	
	public void deleteByEmployeeAndTask(Employee emp , Task task);
	
	public EmployeeTaskMapper findByEmployeeAndTask(Employee emp , Task task);

}
