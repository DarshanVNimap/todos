package com.todoApp.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
	
	@NotBlank
	@Size(min = 5 , max = 50 , message = "title length should be between 5 to 50")
	private String title;
	
	@NotBlank
	@Size(min = 5 , max = 50 , message = "title length should be between 10 to 255")
	private String description;
	
	@NotBlank
	private LocalDate startAt;
	
	@NotBlank
	private LocalDate endAt;
	

}
