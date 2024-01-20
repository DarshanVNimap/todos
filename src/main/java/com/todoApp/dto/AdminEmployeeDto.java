package com.todoApp.dto;

import com.todoApp.Utils.ErrorMessageConstant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminEmployeeDto {
	
	@NotBlank(message = ErrorMessageConstant.INVALID_NAME)
	private String name;
	
	@Email(message = ErrorMessageConstant.INVALID_EMAIL)
	@NotBlank(message = ErrorMessageConstant.INVALID_EMAIL)
	private String email;
	
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" , message = ErrorMessageConstant.INVALID_PASSWORD_FORMATE)
	@NotBlank(message = ErrorMessageConstant.INVALID_PASSWORD)
	private String password;
	
	@NotBlank(message = ErrorMessageConstant.INVALID_ROLE)
	private Integer roleId;

}
