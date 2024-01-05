package com.todoApp.dto;

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

	@NotBlank(message = "please enter email")
	@Email(message = "Please Enter valid email!")
	private String email;
	
	@NotBlank(message = "please enter password")
	private String password;
}
