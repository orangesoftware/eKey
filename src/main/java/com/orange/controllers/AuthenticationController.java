package com.orange.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.orange.beans.request.UserRequest;
import com.orange.beans.response.UserResponse;
import com.orange.entity.UserLogins;
import com.orange.repository.UserRepository;
import com.orange.security.JwtAuthenticationService;

@RestController
public class AuthenticationController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtAuthenticationService jwtAuthenticationService;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/auth/login")
	public ResponseEntity<UserResponse> signin(@RequestBody UserRequest credentials){
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(credentials.getUserName(),credentials.getPassword()));
			
			List<String> list = new ArrayList<>();
			UserLogins theUser = userRepository.findByUserName(credentials.getUserName());
			if ( theUser == null) {
				throw new UsernameNotFoundException("Username " + credentials.getUserName() + " not found");
				
			}
			list.add("ADMIN"); // here need to get roles from table
			String token = jwtAuthenticationService.createToken(credentials.getUserName(), list);
			UserResponse userResponse = new UserResponse();
			userResponse.setUserName(credentials.getUserName());
			userResponse.setToken(token);
			
			return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
		}
		catch(AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied");
		}
	}
}
