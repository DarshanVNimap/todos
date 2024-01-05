package com.todoApp.dto;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto
{
	
	private String message;
	private HttpStatus status;
//	@CurrentTimestamp(timing = GenerationTiming.ALWAYS)
	private Date time;

}
