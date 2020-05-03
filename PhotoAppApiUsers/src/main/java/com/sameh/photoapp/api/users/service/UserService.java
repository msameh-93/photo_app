package com.sameh.photoapp.api.users.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sameh.photoapp.api.users.dto.UserDto;
import com.sameh.photoapp.api.users.model.UserEntity;
import com.sameh.photoapp.api.users.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	public UserDto createUser(UserDto user) {
		user.setUserId(UUID.randomUUID().toString());
		
		ModelMapper modelMapper= new ModelMapper();	//Maps using field names (getters & Setters)
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserEntity userEntity= modelMapper.map(user, UserEntity.class);
		userEntity.setEncryptedPassword("TEST");
		userRepo.save(userEntity);
		
		return modelMapper.map(userRepo.save(userEntity), UserDto.class);
	}
}
