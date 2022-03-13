package com.reba.control;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reba.config.JwtTokenUtil;
import com.reba.converters.UserConverter;
import com.reba.entities.JwtRequest;
import com.reba.entities.JwtResponse;
import com.reba.entities.User;
import com.reba.pojo.UserPojo;
import com.reba.service.UserServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/*
	 * Methods added for user login and logout which will call service layer methods
	 * This methods take user pojo, convert to user entity and pass to service layer
	 * The returned entity from service layer is converted back to entity
	 */
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		UserPojo user = new UserPojo(userDetails.getUsername(),userDetails.getPassword());
		
		User userE = userService.findUserByUserName(userDetails.getUsername());
		UserPojo userP = UserConverter.convert(userE);
		
		user.setUserId(userP.getUserId());
		user.setUserEmail(userP.getUserEmail());
		user.setUserMobile(userP.getUserMobile());
		user.setUserCity(userP.getUserCity());
		user.setUserRole(userP.getUserRole());

		return ResponseEntity.ok(new JwtResponse(token, user));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<UserPojo> loginUser(@Valid @RequestBody UserPojo userP){
		logger.info("Entering loginUser()");
		User userEntity = UserConverter.convertToEntity(userP);
		User userE = userService.loginUser(userEntity);
		UserPojo userPojo = UserConverter.convert(userE);
		return new ResponseEntity<UserPojo>(userPojo,HttpStatus.FOUND);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<UserPojo> logoutUser(@Valid @RequestBody UserPojo userP){
		logger.info("Entering logoutUser()");
		User userEntity = UserConverter.convertToEntity(userP);
		User userE = userService.logoutUser(userEntity);
		UserPojo userPojo = UserConverter.convert(userE);
		return new ResponseEntity<UserPojo>(userPojo,HttpStatus.FOUND);
	}
	
}
