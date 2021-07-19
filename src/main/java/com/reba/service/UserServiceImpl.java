package com.reba.service;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reba.entities.User;
import com.reba.exception.UserIncorrectPasswordException;
import com.reba.exception.UserNotFoundException;
import com.reba.repo.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	UserRepository userRepo;
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	/*
	 * This method is for login the user It will first check if user exists,and then
	 * check if password is valid
	 */
	
	@Override
	public User loginUser(User user) {
		logger.info("Entering service loginUser()");
		Optional<User> userOpt = userRepo.findById(user.getUserId());
		if(userOpt.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		}
		
		logger.info("User found in service loginUser(), now checking password");
		User userEntity = userOpt.get();
		if(!userEntity.getUserPassword().equals(user.getUserPassword())) {
			throw new UserIncorrectPasswordException("Incorrect Password");
		}
		return userEntity;
	}
	
	//This method will logout the user

	@Override
	public User logoutUser(User user) {
		logger.info("Entering service logoutUser()");
		Optional<User> userOpt = userRepo.findById(user.getUserId());
		if(userOpt.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		}
		return userOpt.get();
	}
	
	public User findUserByUserName(String username) {
		return userRepo.findByUserName(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(),
				new ArrayList<>());
	}

}
