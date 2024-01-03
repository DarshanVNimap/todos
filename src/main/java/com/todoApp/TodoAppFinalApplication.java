package com.todoApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class TodoAppFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppFinalApplication.class, args);
	}

}
