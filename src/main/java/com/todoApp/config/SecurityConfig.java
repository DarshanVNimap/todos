package com.todoApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.todoApp.jwtUtils.JwtAuthFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver exceptionResolver;

	@Bean
	UserDetailsService userDetails() {
		return new CustomeUserDetailService();
	}

	@Bean
	BCryptPasswordEncoder bcryptPassword() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

		provider.setUserDetailsService(userDetails());
		provider.setPasswordEncoder(bcryptPassword());

		return provider;
	}

	@Bean
	JwtAuthFilter jwtAuthenticationFilter() {
		return new JwtAuthFilter(exceptionResolver);
	}

	@Bean
	AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain secutiryFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(request -> request.requestMatchers("/swagger-ui/**", "/api/auth/**").permitAll()
						.anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(daoAuthenticationProvider())
				.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		;

		return http.build();
	}

}
