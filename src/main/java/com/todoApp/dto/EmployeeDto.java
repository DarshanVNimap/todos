package com.todoApp.dto;

import com.todoApp.Utils.ErrorMessageConstant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeDto {
	
	@NotBlank(message = ErrorMessageConstant.INVALID_NAME)
	private String name;
	
	@Email(message = ErrorMessageConstant.INVALID_EMAIL)
	@NotBlank(message = ErrorMessageConstant.INVALID_EMAIL)
	private String email;
	
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" , message = ErrorMessageConstant.INVALID_PASSWORD_FORMATE)
	@NotBlank(message = ErrorMessageConstant.INVALID_PASSWORD)
	private String password;
	
}
