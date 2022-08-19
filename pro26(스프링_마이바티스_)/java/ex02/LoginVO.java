package com.spring.ex02;

import java.sql.Date;

public class LoginVO {

	private String userID;
	private String userName;
	private String email;
	
	public LoginVO() {
		
	}
	
	
	public LoginVO(String userID, String userName) {
		this.userID = userID;
		this.userName = userName;
	}
	
	public LoginVO(String userID, String userName, String email) {
		this.userID = userID;
		this.userName = userName;
		this.email = email;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


}
