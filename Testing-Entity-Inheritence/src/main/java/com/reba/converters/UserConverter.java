package com.reba.converters;

import com.reba.entities.User;
import com.reba.pojo.UserPojo;

public class UserConverter {
	
	//Two static methods defined in this class
	//One to convert user entity to user pojo
	//Another one to convert user pojo to user entity
	
	public static UserPojo convert(User user) {
		UserPojo userPojo = new UserPojo();
		userPojo.setUserId(user.getUserId());
		userPojo.setUserName(user.getUserName());
		userPojo.setUserEmail(user.getUserEmail());
		userPojo.setUserPassword(user.getUserPassword());
		userPojo.setUserMobile(user.getUserMobile());
		userPojo.setUserRole(user.getUserRole());
		userPojo.setUserCity(user.getUserCity());
		return userPojo;
	}

	public static User convertToEntity(UserPojo userPojo) {
		User user = new User();
		user.setUserId(userPojo.getUserId());
		user.setUserName(userPojo.getUserName());
		user.setUserEmail(userPojo.getUserEmail());
		user.setUserPassword(userPojo.getUserPassword());
		user.setUserMobile(userPojo.getUserMobile());
		user.setUserRole(userPojo.getUserRole());
		user.setUserCity(userPojo.getUserCity());
		return user;
	}
	
}
