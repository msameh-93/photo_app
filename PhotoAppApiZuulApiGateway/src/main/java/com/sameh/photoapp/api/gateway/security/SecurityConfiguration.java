package com.sameh.photoapp.api.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthenticationEntry authEntry;
	@Autowired
	private Environment env;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Stateless sessions so client do not rely on stored headers
		//server requires new 'state' or headers to reauthorize each request
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.csrf().disable()
			.headers().frameOptions().disable()
			.and()
			.exceptionHandling().authenticationEntryPoint(authEntry)
			.and()
			.addFilter(new AuthorizationFilter(authenticationManager(), env))
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/users-webservice/api/users").permitAll()
				.antMatchers(HttpMethod.POST, "/users-webservice/api/users/signin").permitAll()
				.antMatchers(HttpMethod.GET, "/users-webservice/actuator/*").permitAll()
				.antMatchers("/users-webservice/h2-console").permitAll()
				.antMatchers("/actuator/*").permitAll()
				.anyRequest().authenticated();
	}
}
