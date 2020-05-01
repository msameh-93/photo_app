package com.sameh.photoapp.api.users.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sameh.photoapp.api.users.dto.UserDto;
import com.sameh.photoapp.api.users.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	public UserDto createUser(UserDto user) {
		user.setUserID(UUID.randomUUID().toString());
		return null;
	}
}
