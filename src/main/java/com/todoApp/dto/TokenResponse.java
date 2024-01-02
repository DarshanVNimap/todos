package com.todoApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TokenResponse {
	
	private String jwtToken;
	private String refreshToken;
	private Integer userId;
	private String userName;
	private String msg;

}
