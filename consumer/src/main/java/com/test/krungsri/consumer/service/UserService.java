package com.test.krungsri.consumer.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
	
	public UserDetails loadUserByUsernameAndPassword(String username, String passwor);

}
