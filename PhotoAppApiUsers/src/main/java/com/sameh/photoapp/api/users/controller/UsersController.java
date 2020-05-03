package com.sameh.photoapp.api.users.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sameh.photoapp.api.users.dto.UserDto;
import com.sameh.photoapp.api.users.model.SignUpRequest;
import com.sameh.photoapp.api.users.model.SignUpResponse;
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
		return "Working on port: " + env.getProperty("local.server.port")
			+ " & JWT: " + env.getProperty("token.secret")
			+ "\n" + env.getProperty("spring.rabbitmq.host")
			+ ":"+ env.getProperty("spring.rabbitmq.port");
	}
	@PostMapping(
			consumes= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE} 
			)
	public ResponseEntity<?> createUser(@RequestBody@Valid SignUpRequest user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<String>("Not valid object @controller", HttpStatus.BAD_REQUEST);
		}
		
		ModelMapper modelMapper= new ModelMapper();	//Maps using field names (getters & Setters)
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto= modelMapper.map(user, UserDto.class);
		UserDto userResponse= userServ.createUser(userDto);
		
		return new ResponseEntity<SignUpResponse>(modelMapper.map(userResponse, SignUpResponse.class), HttpStatus.CREATED);
	}
}
