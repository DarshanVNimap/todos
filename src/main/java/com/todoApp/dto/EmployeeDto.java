package com.todoApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto {
	
	@NotBlank(message = "Please enter name")
	private String name;
	
	@Email(message = "Please enter valid email")
	@NotBlank(message = "plese enter email")
	private String email;
	
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" , message = "At least one upper case , At least one lower case, At least one digit , At least one special character , Minimum eight in length")
	@NotBlank(message = "please enter password")
	private String password;

}
