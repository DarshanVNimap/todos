package com.todoApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
@EnableCaching
public class TodoAppFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppFinalApplication.class, args);
	}

}
