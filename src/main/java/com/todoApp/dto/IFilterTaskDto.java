package com.todoApp.dto;

import java.time.LocalDate;

public interface IFilterTaskDto {
	
	public String getTitle();
	public String getDescription();
	public LocalDate getStartDate();
	public LocalDate getEndDate();
	public String getStatus();
	public LocalDate getAssignDate();

}
