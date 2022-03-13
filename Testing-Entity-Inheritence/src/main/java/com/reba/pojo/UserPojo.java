package com.reba.pojo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserPojo {

	private int userId;
	
	@NotNull
	@Size(min = 3, max = 60)
	private String userName;
	
	@NotNull
	@Size(min = 3, max = 60)
	private String userPassword;
	
	@NotNull
	@Size(min = 6, max = 8)
	private String userRole;
	
	@NotNull
	@Size(min = 10, max = 10)
	private String userMobile;
	
	@NotNull
	@Email
	private String userEmail;
	
	@NotNull
	@Size(min = 3, max = 20)
	private String userCity;
	
	public UserPojo() {
		
	}
	
	public UserPojo(String username, String password) {
		this.userName = username;
		this.userPassword = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	
}
