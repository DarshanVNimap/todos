package com.todoApp.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.todoApp.dto.IFilterTaskDto;
import com.todoApp.entity.Employee;
import com.todoApp.entity.EmployeeTaskMapper;
import com.todoApp.entity.Task;

public interface EmployeeTaskMapperRepository extends JpaRepository<EmployeeTaskMapper, Integer>{
	
	public List<EmployeeTaskMapper> findByEmployee(Employee employee);
	
	public List<EmployeeTaskMapper> findByTask(Task task);
	
	public void deleteByEmployeeAndTask(Employee emp , Task task);
	
	public Optional<EmployeeTaskMapper> findByEmployeeAndTask(Employee emp , Task task);
	
	
	@Query(value = "SELECT t.title AS title, t.description AS description, t.start_at AS startDate , t.end_at AS endDate, etm.status  AS status, etm.assign_at AS assignDate\r\n"
			+ "FROM task t\r\n"
			+ "JOIN employee_task_mapper etm ON t.id = etm.task_id\r\n"
			+ "JOIN employee e ON e.id = etm.employee_id\r\n"
			+ "WHERE 1 = 1\r\n"
			+ "  AND (etm.status IS NULL OR etm.status = ?1)\r\n"
			+ "  AND (t.start_at IS NULL OR t.start_at >= ?2)\r\n"
			+ "  AND (t.end_at IS NULL OR t.end_at <= ?3)\r\n"
			+ "  AND (etm.employee_id IS NULL OR etm.employee_id = ?4);" , nativeQuery = true)
	public List<IFilterTaskDto> filterAssignedTask(@Param("status") short status , LocalDate startDate , LocalDate endDate , Integer employeeId);

}
