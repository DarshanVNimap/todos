package com.todoApp.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto{

	@NotBlank(message = "Please enter title")
	@Size(min = 5, max = 50, message = "title length should be between 5 to 50")
	private String title;

	@NotBlank(message = "Please enter description")
	@Size(min = 5, max = 50, message = "title length should be between 10 to 255")
	private String description;

	@NotNull(message = "Please enter start date")
	
	private LocalDate startAt;

	@NotNull(message = "Please enter end date")
	private LocalDate endAt;

}
