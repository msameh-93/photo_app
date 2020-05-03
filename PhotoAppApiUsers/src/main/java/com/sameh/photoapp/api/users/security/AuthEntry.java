package com.sameh.photoapp.api.users.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntry implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		String jsonInvalidLoginResponse= "Not Authorized, SOrry";
		
		response.setContentType("application/json");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().print(jsonInvalidLoginResponse);
	}
}
