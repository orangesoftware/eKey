package com.orange.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.orange.entity.UserLogins;
import com.orange.repository.UserRepository;

@Service
public class UserDetailServiceImpl  implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		UserLogins theUser= userRepository.findByUserName(username);
		
		UserDetails user = new User(username,theUser.getPassword(),AuthorityUtils.createAuthorityList("ADMIN"));
		return user;
	}
}
