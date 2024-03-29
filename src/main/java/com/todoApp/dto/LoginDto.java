package com.todoApp.dto;

import com.todoApp.Utils.ErrorMessageConstant;

// checked

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

	@NotBlank(message = ErrorMessageConstant.INVALID_EMAIL)
	@Email(message = ErrorMessageConstant.INVALID_EMAIL)
	private String email;
	
	@NotBlank(message = ErrorMessageConstant.INVALID_PASSWORD)
	private String password;
}
