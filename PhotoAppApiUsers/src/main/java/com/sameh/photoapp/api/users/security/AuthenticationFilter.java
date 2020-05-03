package com.sameh.photoapp.api.users.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sameh.photoapp.api.users.dto.UserDto;
import com.sameh.photoapp.api.users.model.SignInRequest;
import com.sameh.photoapp.api.users.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//triggered by /login endpoint
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private UserService userServ;
	@Autowired
	private Environment env;
	@Autowired
	public AuthenticationFilter(UserService userServ, AuthenticationManager authManager) {
		this.userServ= userServ;
		super.setAuthenticationManager(authManager);
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
//			SignInRequest signin= new SignInRequest(request.getParameter("email"), request.getParameter("password"));
			SignInRequest signin= new ObjectMapper()
					.readValue(request.getInputStream(), SignInRequest.class);
			
			return getAuthenticationManager()	//provided by spring security
					.authenticate(new UsernamePasswordAuthenticationToken(
							signin.getEmail(), signin.getPassword(), new ArrayList<>())
							);
			
		} catch (IOException e) {
			
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String email= ((User) authResult.getPrincipal()).getUsername();
		
		UserDto userDto= userServ.getUserDetailsByEmail(email);
		
		String token= Jwts.builder()
				.setSubject(userDto.getUserId())
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiry"))))
				.signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
				.compact();
		response.addHeader("token", token);
		response.addHeader("userId", userDto.getUserId());
		
	}
}
