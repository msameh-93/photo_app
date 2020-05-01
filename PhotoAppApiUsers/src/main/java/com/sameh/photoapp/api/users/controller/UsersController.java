package com.sameh.photoapp.api.users.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sameh.photoapp.api.users.model.SignUpRequest;
import com.sameh.photoapp.api.users.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	@Autowired
	private Environment env;
	@Autowired
	private UserService userServ;
	
	@GetMapping("/status/check")
	public String status() {
		return "Working on port: " + env.getProperty("local.server.port");
	}
	@PostMapping
	public String createUser(@RequestBody@Valid SignUpRequest user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "NOT VALID";
		}
		return "Create user";
	}
}
