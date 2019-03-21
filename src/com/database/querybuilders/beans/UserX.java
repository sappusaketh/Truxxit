package com.database.querybuilders.beans;

import java.io.Serializable;

public class UserX implements Serializable {
	
	Integer userId;
	String userName;
	
	public UserX(Integer userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
