package com.orange.beans.request;

import java.io.Serializable;

public class UserRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6442847187539719063L;
	private String userName;
	private String password;
	private String email;
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
