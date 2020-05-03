package com.sameh.photoapp.api.users.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sameh.photoapp.api.users.dto.UserDto;
import com.sameh.photoapp.api.users.model.UserEntity;
import com.sameh.photoapp.api.users.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	public UserDto createUser(UserDto user) {
		user.setUserId(UUID.randomUUID().toString());
		user.setEncryptedPassword(bCrypt.encode(user.getPassword()));
		
		ModelMapper modelMapper= new ModelMapper();	//Maps using field names (getters & Setters)
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserEntity userEntity= modelMapper.map(user, UserEntity.class);
		userRepo.save(userEntity);
		
		return modelMapper.map(userRepo.save(userEntity), UserDto.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		UserEntity userFound= userRepo.findByEmail(username);
		if(userFound==null) {
			//Provided by spring
			throw new UsernameNotFoundException(username);
		}
		
		return new User(userFound.getEmail(), userFound.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}
	
	public UserDto getUserDetailsByEmail(String email) throws UsernameNotFoundException {
		UserEntity userFound= userRepo.findByEmail(email);
		if(userFound==null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new ModelMapper().map(userFound, UserDto.class);
	}
}
