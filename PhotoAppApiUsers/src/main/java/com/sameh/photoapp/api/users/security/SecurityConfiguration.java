package com.sameh.photoapp.api.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sameh.photoapp.api.users.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthEntry authEntry;
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	@Autowired
	private UserService usersServ;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(authEntry)
			.and()
			.headers().frameOptions().disable()
			.and()
			.addFilter(getAuthFilter());
	}
	
	@Bean
	public AuthenticationFilter getAuthFilter() throws Exception {
		
		AuthenticationFilter authFilter= new AuthenticationFilter(usersServ, authenticationManager());
		//authenticationManager provided by spring security
		//authenticationManager is retrieved by getAuthenticationManager in filter
		//Autowired through authentication filter constructor
//		authFilter.setAuthenticationManager(authenticationManager());	
		
		authFilter.setFilterProcessesUrl("/api/users/signin");
		return authFilter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usersServ).passwordEncoder(bCrypt);
	}
}
