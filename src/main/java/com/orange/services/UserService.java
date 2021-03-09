package com.orange.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.orange.beans.request.UserRequest;
import com.orange.beans.response.UserResponse;
import com.orange.entity.UserLogins;
import com.orange.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserResponse addUser(UserRequest user) {
		UserLogins logins = new UserLogins();
		UserResponse response=null;
		logins.setPassword(passwordEncoder.encode(user.getPassword()));
		logins.setUserName(user.getUserName());
		logins.setEmail(user.getEmail());
		logins.setCreatedBy("api");
		userRepository.save(logins);
		user.setId( logins.getId());
		response = new UserResponse(logins.getId(),user.getUserName(),"");
		return response;
	}
}