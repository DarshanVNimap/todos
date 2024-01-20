package com.todoApp.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.todoApp.dto.EmployeeDto;
import com.todoApp.entity.Employee;
import com.todoApp.repository.EmployeeRepository;
import com.todoApp.service.AuthenticationService;
import com.todoApp.service.JwtAuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomeSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private JwtAuthService jwtService;
//	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationService authService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub

		String redirectUrl = null;
		Employee e = null;
		String jwtToken = null;
		System.err.println("Here theere");
//		System.err.println(REQUE);
		if (authentication.getPrincipal() instanceof DefaultOAuth2User) {
			
			System.out.println(authentication.getPrincipal());

			DefaultOAuth2User userDetail = (DefaultOAuth2User) authentication.getPrincipal();
			


			String username = userDetail.getAttribute("email") != null ? userDetail.getAttribute("email")
					: userDetail.getAttribute("login") + "@gmail.com";
			
			if (empRepo.findByEmail(username).isEmpty()) {
			    String name = userDetail.getAttribute("name");
				
				EmployeeDto emp = EmployeeDto.builder().email(username).name(name).password("Mysecret@123").build();
			    try {
			        authService.register(emp);
			    } catch (DataIntegrityViolationException ex) {
			        ex.printStackTrace(); // Log the exception details or handle it appropriately
			    } catch (Exception e1) {
			        e1.printStackTrace(); // Log the exception details or handle it appropriately
			    }
			}
			
//			e = empRepo.findByEmail(username).get();
//			
//			jwtToken = jwtService.generateToken(new CustomeUserDetail(e));
			
		}
		redirectUrl = "/";
		new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
	}

}
