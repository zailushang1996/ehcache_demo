package com.mengdee.manager.entity;

public class User {

	private String userId;
	private String username;
	

	public User(String userId, String username) {
		super();
		this.userId = userId;
		this.username = username;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
