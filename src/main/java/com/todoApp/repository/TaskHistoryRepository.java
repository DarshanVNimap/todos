package com.todoApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoApp.entity.TaskHistory;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Integer>{

}
