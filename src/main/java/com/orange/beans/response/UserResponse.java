package com.orange.beans.response;

import java.io.Serializable;

public class UserResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6719915624294845596L;
	private long id;
	private String userName;
	private String token;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserResponse(long id, String userName, String token) {
		super();
		this.id = id;
		this.userName = userName;
		this.token = token;
	}
	
	
}


