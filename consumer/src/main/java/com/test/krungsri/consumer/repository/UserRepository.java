package com.test.krungsri.consumer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.krungsri.consumer.model.UserModel;

@Repository 
public interface UserRepository extends CrudRepository<UserModel, String> {
	
	

}
