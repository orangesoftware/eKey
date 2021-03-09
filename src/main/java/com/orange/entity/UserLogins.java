package com.orange.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserLogins {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	@Column(name="username")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	@Column(name="createdby")
	private String createdBy;
	@Column(name="creeateddate")
	private Date createdDate;
	@Column(name="updatedby")
	private String updatedBy;
	@Column(name="updatedDate")
	private Date updatedDate;
	
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDatte() {
		return updatedDate;
	}
	public void setUpdatedDatte(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
