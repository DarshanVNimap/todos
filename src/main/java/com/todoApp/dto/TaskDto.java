package com.todoApp.dto;

import java.time.LocalDate;

import com.todoApp.Utils.ErrorMessageConstant;

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

	@NotBlank(message = ErrorMessageConstant.INVALID_TITLE)
	@Size(min = 5, max = 50, message = ErrorMessageConstant.INVALID_TITLE_LENGTH)
	private String title;

	@NotBlank(message = ErrorMessageConstant.INVALID_DESCRIPTION)
	@Size(min = 5, max = 50, message = ErrorMessageConstant.INVALID_DESCRIPTION_LENGTH)
	private String description;

	@NotNull(message = ErrorMessageConstant.INVALID_DATE)
	private LocalDate startAt;

	@NotNull(message = ErrorMessageConstant.INVALID_DATE)
	private LocalDate endAt;

}
