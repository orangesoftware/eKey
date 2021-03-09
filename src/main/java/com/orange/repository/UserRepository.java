package com.orange.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orange.entity.UserLogins;

public interface UserRepository extends JpaRepository<UserLogins,Long>{
	public UserLogins findByUserName(String userName);
}
