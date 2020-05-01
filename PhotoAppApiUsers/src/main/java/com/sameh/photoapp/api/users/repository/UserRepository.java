package com.sameh.photoapp.api.users.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sameh.photoapp.api.users.model.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{
	
	
}
