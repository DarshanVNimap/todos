package com.todoApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolePermissionResponseDto {
	
	private Integer roleId;
	private Integer permissionId;

}
