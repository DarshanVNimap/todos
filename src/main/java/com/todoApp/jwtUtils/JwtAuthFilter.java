package com.todoApp.jwtUtils;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter{

	@Autowired
	private JwtAuthService jwtService;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	
	private final HandlerExceptionResolver exceptionResolver;

	@Override
	protected void doFilterInternal(
			@NotNull HttpServletRequest request, 
			@NotNull HttpServletResponse response, 
			@NotNull FilterChain filterChain)
			throws ServletException, IOException {
		
		final String getHeader = request.getHeader("Authorization");
		final String getToken ;
		final String username;
		
		try {
		
		if(getHeader == null || !getHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return ;
		}
		
		getToken = getHeader.substring(7);
		username = jwtService.extarctUsername(getToken);
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetail =userDetailService.loadUserByUsername(username);
			if(jwtService.isValidToken(getToken, userDetail)) {
				
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null , userDetail.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
		
		}
		catch(Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			exceptionResolver.resolveException(request, response, null, ex);
		}
	}
	

}
