package com.reba.service;

import com.reba.entities.User;

public interface UserService {
	
	public User loginUser(User user);
	
	public User logoutUser(User user);
	
}
