package com.orange.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.orange.beans.request.UserRequest;
import com.orange.beans.response.UserResponse;
import com.orange.services.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/users/add-user")
	public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest user){
		UserResponse userResponse = userService.addUser(user);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
	}
}
