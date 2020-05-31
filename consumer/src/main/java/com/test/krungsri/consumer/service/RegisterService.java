package com.test.krungsri.consumer.service;

import java.util.Optional;

import com.test.krungsri.consumer.model.UserModel;

public interface RegisterService {
	
	public Optional<UserModel>  findById(String username);
	
	public UserModel saveModel(UserModel model);

}
