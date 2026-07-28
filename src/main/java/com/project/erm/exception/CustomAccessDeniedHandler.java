package com.project.erm.exception;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setHeader("acces-denied-header", "Authorisation failed");
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType("application/json");
		String jsonResponse = String.format(
				"{\"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"description\": \"%s\", \"path\": \"%s\"}",
				LocalDateTime.now(),
				HttpStatus.FORBIDDEN.value(),
				HttpStatus.FORBIDDEN.getReasonPhrase(), accessDeniedException.getMessage(), request.getRequestURI());
		response.getWriter().write(jsonResponse);
	}
}
