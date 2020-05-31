package com.test.krungsri.consumer.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.krungsri.consumer.model.UserModel;
import com.test.krungsri.consumer.repository.UserRepository;
import com.test.krungsri.consumer.service.RegisterService;

@Service
public class RegisterServiceImpl  implements RegisterService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<UserModel> findById(String username) {
		return userRepository.findById(username);
	}

	@Override
	public UserModel saveModel(UserModel model) {
		return userRepository.save(model);
	}


}
