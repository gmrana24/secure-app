package com.project.erm.exception;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setHeader("authentication-error", "Authentication Failed");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json");
		String jsonResponse = String.format(
				"{\"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"description\": \"%s\", \"path\": \"%s\"}",
				LocalDateTime.now(),
				HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED.getReasonPhrase(), authException.getMessage(), request.getRequestURI());
		response.getWriter().write(jsonResponse);
	}
}
